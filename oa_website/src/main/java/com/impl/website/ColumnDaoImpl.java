package com.impl.website;

import java.util.LinkedHashMap;
import java.util.List;

import org.oa_bean.Page;
import org.oa_bean.website.ColumnObj;
import org.springframework.stereotype.Repository;

import com.dao.website.ColumnDao;
import com.impl.BaseDaoImpl;
@Repository(value="ColumnDao")
public class ColumnDaoImpl extends BaseDaoImpl implements ColumnDao{

	@Override
	public ColumnObj findColumnById(Integer id) {
		return find(ColumnObj.class, id);
	}
	public Page<ColumnObj> findColumnPage(Page<ColumnObj> page, String wheresql,
			List<Object> queryParams, LinkedHashMap<String, String> orderby) {
		return getScrollData(page, ColumnObj.class, wheresql, queryParams, orderby);
	}

	@Override
	public void addColumn (ColumnObj column,Integer modelId){
			
		String sql = "INSERT INTO w_column "
				+ "(column_div,plugin_id,module_ids,module_names,model_url,page_url,model_id) VALUES "
				+ "('"+column.getColumnDiv() +"',"+column.getPluginId()+",'"+column.getModuleIds()+"','"+column.getModuleNames()+"','"+column.getModelUrl()+"','"+column.getPageUrl()+"',"+modelId+")";
		createSqlMethod(sql).executeUpdate();
	}
	 
	@Override
	public void updateColumn (ColumnObj column,Integer modelId){
		String sql = " UPDATE w_column SET "
				+ " column_div='"+column.getColumnDiv()+"',plugin_id="+column.getPluginId()+ ", "
				+ " module_ids='"+column.getModuleIds()+"',module_names='"+column.getModuleNames()+"',"
				+ " model_url='"+column.getModelUrl()+"', page_url='"+column.getPageUrl()+"', "
				+ " model_id = " +modelId + " WHERE column_id = "+column.getColumnId();
		createSqlMethod(sql).executeUpdate();
	}
	 
	@Override
	public void deleteColumnByLayout(Integer layoutId) {
		String sql = "DELETE FROM w_column WHERE layout_id ="+layoutId;
		createSqlMethod(sql).executeUpdate();
	}
	@Override
	public void deleteColumnByPlugin(Integer pluginId) {
		String sql = "DELETE FROM w_column WHERE plugin_id ="+pluginId;
		createSqlMethod(sql).executeUpdate();
	}

	@Override
	public void deleteColumnByModel(Integer modelId){
		String sql = "DELETE FROM w_column WHERE model_id ="+modelId;
		createSqlMethod(sql).executeUpdate();
	}
	 
}
