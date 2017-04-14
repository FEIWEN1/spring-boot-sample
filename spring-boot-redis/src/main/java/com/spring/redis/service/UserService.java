package com.spring.redis.service;

import java.util.Set;

import org.springframework.data.redis.core.ZSetOperations.TypedTuple;

import com.spring.redis.bean.User;

/**
 * ClassName:UserService <br/>
 * Title:
 * <p>
 * redis各个方法调用
 * </p>
 * <br/>
 * Date: 2017年4月11日 下午5:22:35 <br/>
 * 
 * @author shichao
 * @version
 * 
 */
public interface UserService {

	/**
	 * 
	 * @Title:<p>存储</p><br/>
	 * @author shichao
	 * @param user
	 */
	void saveUser(User user);

	/**
	 * 
	 * @Title:<p>获取</p><br/>
	 * @author shichao
	 * @param username
	 * @return
	 */
	User getUser(String username);

	/**
	 * 
	 * @Title:<p>删除</p><br/>
	 * @author shichao
	 * @param username
	 */
	void deleteUser(String username);

	/**
	 * 
	 * @Title:<p>增加</p><br/>
	 * @author shichao
	 * @param key
	 * @param increment
	 *            增量
	 * @return
	 */
	long increment(String key, long increment);

	/**
	 * 
	 * @Title:<p>list队列取出</p><br/>
	 * @author shichao
	 * @param key
	 * @return
	 */
	User popList(String key);

	/**
	 * 
	 * @Title:<p>list队列插入</p><br/>
	 * @author shichao
	 * @param key
	 * @param user
	 */
	void pushList(String key, User user);

	/**
	 * 
	 * @Title:<p>set集合添加</p><br/>
	 * @author shichao
	 * @param key
	 * @param user
	 * @return
	 */
	Long pushSet(String key, User user);

	/**
	 * 
	 * @Title:<p>set集合删除</p><br/>
	 * @author shichao
	 * @param key
	 * @param user
	 * @return
	 */
	Long removeSet(String key, User user);

	/**
	 * 
	 * @Title:<p>得到set集合</p><br/>
	 * @author shichao
	 * @param key
	 * @return
	 */
	Set<Object> getSets(String key);

	/**
	 * 
	 * @Title:<p>当前set集合是否有此成员</p><br/>
	 * @author shichao
	 * @param key
	 * @param obj
	 * @return
	 */
	boolean isMember(String key, Object obj);

	/**
	 * 
	 * @Title:<p>弹出set集合一个object</p><br/>
	 * @author shichao
	 * @param key
	 * @return
	 */
	User popSet(String key);

	/**
	 * 
	 * @Title:<p>SortedSet有序集合添加</p><br/>
	 * @author shichao
	 * @param key
	 * @param user
	 * @param score
	 * @return
	 */
	Boolean pushZset(String key, User user, double score);

	/**
	 * 
	 * @Title:<p>获取SortedSet有序集合第一个元素，按score从小到大排序</p><br/>
	 * @author shichao
	 * @param key
	 * @return
	 */
	TypedTuple<Object> getZset(String key);

	/**
	 * 
	 * @Title:<p>SortedSet有序集合大小</p><br/>
	 * @author shichao
	 * @param key
	 * @return
	 */
	Long zCard(String key);

	/**
	 * 
	 * @Title:<p>SortedSet有序集合更改指定元素score，元素不存在则添加到集合里</p><br/>
	 * @author shichao
	 * @param key
	 * @param user
	 * @param increment
	 * @return
	 */
	Double incrementZset(String key, User user, double increment);

	/**
	 * 
	 * @Title:<p>Hash添加</p><br/>
	 * @author shichao
	 * @param key
	 * @param hashKey
	 * @param user
	 */
	void addHash(String key, String hashKey, User user);

	/**
	 * 
	 * @Title:<p>Hash删除</p><br/>
	 * @author shichao
	 * @param key
	 * @param hashKey
	 */
	void deleteHash(String key, String hashKey);

	/**
	 * 
	 * @Title:<p>指定频道发布</p><br/>
	 * @author shichao
	 * @param channel
	 * @param user
	 */
	void publish(String channel, Object user);

}
