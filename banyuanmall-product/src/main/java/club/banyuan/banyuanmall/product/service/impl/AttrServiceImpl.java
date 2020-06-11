package club.banyuan.banyuanmall.product.service.impl;

import club.banyuan.banyuanmall.common.utils.PageUtils;
import club.banyuan.banyuanmall.common.utils.Query;
import club.banyuan.banyuanmall.product.dao.AttrAttrgroupRelationDao;
import club.banyuan.banyuanmall.product.dao.AttrDao;
import club.banyuan.banyuanmall.product.dao.AttrGroupDao;
import club.banyuan.banyuanmall.product.dao.CategoryDao;
import club.banyuan.banyuanmall.product.entity.AttrAttrgroupRelationEntity;
import club.banyuan.banyuanmall.product.entity.AttrEntity;
import club.banyuan.banyuanmall.product.entity.AttrGroupEntity;
import club.banyuan.banyuanmall.product.entity.CategoryEntity;
import club.banyuan.banyuanmall.product.service.AttrService;
import club.banyuan.banyuanmall.product.service.CategoryService;
import club.banyuan.banyuanmall.product.vo.AttrGroupRelationVo;
import club.banyuan.banyuanmall.product.vo.AttrRespVo;
import club.banyuan.banyuanmall.product.vo.AttrVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;


@Service("attrService")
public class AttrServiceImpl extends ServiceImpl<AttrDao, AttrEntity> implements AttrService {

  @Autowired
  AttrAttrgroupRelationDao attrDao;

  @Autowired
  AttrGroupDao attrGroupDao;

  @Autowired
  CategoryDao categoryDao;

  @Autowired
  CategoryService categoryService;

  @Override
  public PageUtils queryPage(Map<String, Object> params) {
    IPage<AttrEntity> page = this.page(
        new Query<AttrEntity>().getPage(params),
        new QueryWrapper<AttrEntity>()
    );

    return new PageUtils(page);
  }

  @Transactional
  @Override
  public void saveAttr(AttrVo attr) {
    AttrEntity attrEntity = new AttrEntity();
    //进行属性值的复制
    BeanUtils.copyProperties(attr, attrEntity);
    //1.保存基本数据
    this.save(attrEntity);
    //2.保存关联关系
    if(attr.getAttrType()== 1){
      AttrAttrgroupRelationEntity attrAttrgroupRelationEntity = new AttrAttrgroupRelationEntity();
      attrAttrgroupRelationEntity.setAttrGroupId(attr.getAttrGroupId());
      attrAttrgroupRelationEntity.setAttrId(attrEntity.getAttrId());

      attrDao.insert(attrAttrgroupRelationEntity);

    }

  }

  @Override
  public PageUtils queryBaseAttrPage(Map<String, Object> params, Long catelogId,
      String type) {

    QueryWrapper<AttrEntity> wrapper = new QueryWrapper<AttrEntity>()
        .eq("attr_type", "base".equalsIgnoreCase(type) ? 1 : 0);
    if (catelogId != 0) {
      wrapper.eq("catelog_id", catelogId);
    }
    String key = (String) params.get("key");
    if (!StringUtils.isEmpty(key)) {
      wrapper.and((wrapp) -> {
        wrapp.eq("attr_id", key).or().like("attr_name", key);
      });
    }

    IPage<AttrEntity> page = this.page(
        new Query<AttrEntity>().getPage(params), wrapper);

    PageUtils pageUtils = new PageUtils(page);
    List<AttrEntity> records = page.getRecords();
    List<AttrRespVo> respVos = records.stream().map(attrEntity -> {
      AttrRespVo attrRespVo = new AttrRespVo();
      BeanUtils.copyProperties(attrEntity, attrRespVo);

      //设置分类和分组的名字
      AttrAttrgroupRelationEntity attr_id = attrDao.selectOne(
          new QueryWrapper<AttrAttrgroupRelationEntity>()
              .eq("attr_id", attrEntity.getAttrId()));
      //分组 如果有type是base 那么我们就进行分组
      if ("base".equalsIgnoreCase(type)) {
        AttrGroupEntity attrGroupEntity = attrGroupDao.selectById(attr_id);
        if (attr_id != null) {
          attrRespVo.setGroupName(attrGroupEntity.getAttrGroupName());
        }
      }

      //分类
      CategoryEntity categoryEntity = categoryDao.selectById(attrEntity.getCatelogId());
      if (categoryEntity != null) {
        attrRespVo.setCatelogName(categoryEntity.getName());
      }
      return attrRespVo;
    }).collect(Collectors.toList());
    pageUtils.setList(respVos);
    return pageUtils;
  }


  @Override
  public AttrRespVo getAttrId(Long attrId) {
    AttrRespVo attrRespVo = new AttrRespVo();
    AttrEntity AttrId = this.getById(attrId);
    BeanUtils.copyProperties(AttrId, attrRespVo);
    //1.设置分组信息
    AttrAttrgroupRelationEntity attGroup = attrDao
        .selectOne(new QueryWrapper<AttrAttrgroupRelationEntity>().eq("attr_id", attrId));
    if (attGroup != null) {
      attrRespVo.setAttrGroupId(attGroup.getAttrGroupId());

      AttrGroupEntity attrGroupEntity = attrGroupDao.selectById(attGroup.getAttrGroupId());
      if (attrGroupEntity != null) {
        attrRespVo.setGroupName(attrGroupEntity.getAttrGroupName());
      }
    }
    //TODO 2.设置分类信息
    Long catelogId = AttrId.getCatelogId();
    Long[] catelogPath = categoryService.findCatelogPath(catelogId);
    attrRespVo.setCatelogPath(catelogPath);

    CategoryEntity categoryEntity = categoryDao.selectById(catelogId);
    if (categoryEntity != null) {
      attrRespVo.setCatelogName(categoryEntity.getName());
    }

    return attrRespVo;
  }

  //查找出关联关系

  /**
   * 根据您分组id查找出关联的所有的基本属性
   * @param attrGroupId
   * @return
   */
  @Override
  public List<AttrEntity> getRelationAttr(Long attrGroupId) {
    List<AttrAttrgroupRelationEntity> entities = attrDao
        .selectList(new QueryWrapper<AttrAttrgroupRelationEntity>()
            .eq("attr_group_id", attrGroupId));
    //收集所有的属性id
    //AttrEnum.ATTR_TYPE_BASE

    List<Long> attrIds=entities.stream().map((attr)->
       attr.getAttrId()
    ).collect(Collectors.toList());
    Collection<AttrEntity> attrEntities = this.listByIds(attrIds);
    return (List<AttrEntity>) attrEntities;
  }

  /**
   * 删除关联关系
   * @param vos
   */
  @Override
  public void deleteRelation(AttrGroupRelationVo[] vos) {
      //实现批量删除
    List<AttrAttrgroupRelationEntity> collect = Arrays.asList(vos).stream().map((item) -> {
      AttrAttrgroupRelationEntity entity = new AttrAttrgroupRelationEntity();
      BeanUtils.copyProperties(item, entity);
      return entity;
    }).collect(Collectors.toList());

    attrDao.deleteBatchIds(collect);
  }

}