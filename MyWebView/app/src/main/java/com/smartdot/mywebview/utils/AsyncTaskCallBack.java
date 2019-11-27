package com.smartdot.mywebview.utils;

public interface AsyncTaskCallBack<T> {

	/**
	 * 当异步任务将要开始执行的时候执行
	 */
	public void onPreExecute();
	
	/**
	 * 当结果返回的时候执行
	 * @param t 返回的结果
	 */
	public void onPostExecute(T t);
	
	/**
	 * 当请求被取消的时候执行
	 */
	public void onCancled();
	
}