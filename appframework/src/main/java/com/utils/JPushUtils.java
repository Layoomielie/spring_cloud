package com.utils;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.push.PushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 极光推送工具类
 * 
 * @author 黄松涛
 * 我也不知道谁写的   感觉挺全的
 */
public class JPushUtils {
	public static final String CATEGORY_NEWS = "schoolnews";
	public static final String CATEGORY_SCHOOL_NOTICE = "schoolnotice";
	public static final String CATEGORY_BK = "bk_notify";
	public static final String CATEGORY_CJ = "cj_notify";
	public static final String CATEGORY_KQ = "kq_notify";
	public static final String CATEGORY_LQ = "lq_notify";
	public static final String CATEGORY_JC = "JC_notify";
	public static final String CATEGORY_KB = "KB_notify";
	public static final String CATEGORY_JY = "jy_notify";
	public static final String CATEGORY_PARENTS_VERIFY = "parents_verify_notify";
	public static final String MasterSecret="7fd7051ce274e62fe77fa17f";
	public static final String AppKey="3acafaec4d0ca10331e4cd31";

	private static PushClient pushClient;
	private static boolean mode = false;// 默认开发者模式

	public static PushClient getPushClient() {
//		ResourceBundle config = ResourceBundle.getBundle("config");
//		mode = Boolean.valueOf(config.getString("jpush.mode"));
		if (pushClient == null) {
			synchronized (JPushUtils.class) {
				if (pushClient == null) {
					pushClient = new PushClient(MasterSecret, AppKey);
//					pushClient = new PushClient(config.getString("jpush.masterSecret"), config.getString("jpush.appKey"));
				}
			}
		}
		return pushClient;
	}

	/**
	 * 给所有的设备发送消息 - 无标题
	 */
	public static PushPayload pushPlatAllNoti(String content) {

		return PushPayload.alertAll(content);
	}

	/**
	 * 根据Alias字符串 发送消息，带参数 - 无标题
	 */
	public static PushPayload pushPlatOneAliasNoti(String alias, String content, Map<String, String> extras) {
		return PushPayload.newBuilder().setPlatform(Platform.all()).setAudience(Audience.alias(alias)).setNotification(Notification.alert(content))
				.setMessage(Message.content(content)).build();
	}

	/**
	 * 根据Alias字符串 发送消息，无参数 - 无标题
	 */
	public static PushPayload pushPlatOneAliasNoti(String alias, String content) {
		return PushPayload.newBuilder().setPlatform(Platform.all()).setAudience(Audience.alias(alias)).setNotification(Notification.alert(content))
				.setMessage(Message.content(content)).build();
	}

	/**
	 * 全部安卓设备 - 推送
	 * 
	 * @param title
	 * @param content
	 * @param extras
	 */
	public static PushPayload pushAndroid(String title, String content, Map<String, String> extras) {
		return PushPayload
				.newBuilder()
				.setPlatform(Platform.android())
				.setAudience(Audience.all())
				.setNotification(
						Notification.newBuilder()
								.addPlatformNotification(AndroidNotification.newBuilder().setAlert(content).setTitle(title).addExtras(extras).build()).build())
				.setMessage(Message.newBuilder().setMsgContent(content).setTitle(title).addExtras(extras).build()).build();
	}

	/**
	 * 指定Tag安卓设备 - 推送
	 *
	 * @param title
	 * @param content
	 * @param extras
	 */
	public static PushPayload pushAndroid(String title, String content, List<String> tags, Map<String, String> extras) {
		return PushPayload
				.newBuilder()
				.setPlatform(Platform.android())
				.setAudience(Audience.tag(tags))
				.setNotification(
						Notification.newBuilder()
								.addPlatformNotification(AndroidNotification.newBuilder().setAlert(content).setTitle(title).addExtras(extras).build()).build())
				.setMessage(Message.newBuilder().setMsgContent(content).setTitle(title).addExtras(extras).build()).build();
	}

	/**
	 * 指定安卓设备推送
	 * 
	 * @param alias
	 * @param title
	 * @param content
	 * @param extras
	 * @return
	 */
	public static PushPayload pushAndroid(String alias, String title, String content, Map<String, String> extras) {
		return PushPayload
				.newBuilder()
				.setPlatform(Platform.android())
				.setAudience(Audience.alias(alias))
				.setNotification(
						Notification.newBuilder()
								.addPlatformNotification(AndroidNotification.newBuilder().setAlert(content).setTitle(title).addExtras(extras).build()).build())
				.setMessage(Message.newBuilder().setMsgContent(content).setTitle(title).addExtras(extras).build()).build();
	}

	/**
	 * 指定安卓设备集合推送
	 * 
	 * @param alias
	 * @param title
	 * @param content
	 * @param extras
	 * @return
	 */
	public static PushPayload pushAndroid(Collection<String> alias, String title, String content, Map<String, String> extras) {
		return PushPayload
				.newBuilder()
				.setPlatform(Platform.android())
				.setAudience(Audience.alias(alias))
				.setNotification(
						Notification.newBuilder()
								.addPlatformNotification(AndroidNotification.newBuilder().setAlert(content).setTitle(title).addExtras(extras).build()).build())
				.setMessage(Message.newBuilder().setMsgContent(content).setTitle(title).addExtras(extras).build()).build();
	}

	/**
	 * 全部ios设备推送
	 * 
	 * @param title
	 * @param content
	 * @param extras
	 * @return
	 */
	public static PushPayload pushIOS(String title, String content, Map<String, String> extras) {
		return PushPayload
				.newBuilder()
				.setPlatform(Platform.ios())
				.setAudience(Audience.all())
				.setNotification(
						Notification
								.newBuilder()
								.addPlatformNotification(IosNotification.newBuilder().setAlert(content).setSound("happy").autoBadge().addExtras(extras).build())
								.build()).setMessage(Message.newBuilder().setMsgContent(content).setTitle(title).addExtras(extras).build())
				.setOptions(Options.newBuilder().setApnsProduction(mode)// 如果为true
																		// 说明是生产,false为开发
						.build()).build();
	}

	/**
	 * 指定Tagios设备 - 推送
	 *
	 * @param title
	 * @param content
	 * @param extras
	 */
	public static PushPayload pushIOS(String title, String content, List<String> tags, Map<String, String> extras) {
		return PushPayload
				.newBuilder()
				.setPlatform(Platform.ios())
				.setAudience(Audience.tag(tags))
				.setNotification(
						Notification
								.newBuilder()
								.addPlatformNotification(IosNotification.newBuilder().setAlert(content).setSound("happy").autoBadge().addExtras(extras).build())
								.build()).setMessage(Message.newBuilder().setMsgContent(content).setTitle(title).addExtras(extras).build())
				.setOptions(Options.newBuilder().setApnsProduction(mode)// 如果为true
						// 说明是生产,false为开发
						.build()).build();
	}

	/**
	 * 给指定ios设备推送
	 * 
	 * @param title
	 * @param alias
	 * @param content
	 * @param extras
	 * @return
	 */
	public static PushPayload pushIOS(String title, String alias, String content, Map<String, String> extras) {
		return PushPayload
				.newBuilder()
				.setPlatform(Platform.ios())
				.setAudience(Audience.alias(alias))
				.setNotification(
						Notification
								.newBuilder()
								.addPlatformNotification(IosNotification.newBuilder().setAlert(content).setSound("happy").autoBadge().addExtras(extras).build())
								.build()).setMessage(Message.newBuilder().setMsgContent(content).setTitle(title).addExtras(extras).build())
				.setOptions(Options.newBuilder().setApnsProduction(mode)// 如果为true
																		// 说明是生产,false为开发
						.build()).build();
	}

	/**
	 * 给指定ios设备集合进行推送
	 * 
	 * @param alias
	 * @param title
	 * @param content
	 * @param extras
	 * @return
	 */
	public static PushPayload pushIOS(Collection<String> alias, String title, String content, Map<String, String> extras) {
		return PushPayload
				.newBuilder()
				.setPlatform(Platform.ios())
				.setAudience(Audience.alias(alias))
				.setNotification(
						Notification
								.newBuilder()
								.addPlatformNotification(IosNotification.newBuilder().setAlert(content).setSound("happy").autoBadge().addExtras(extras).build())
								.build()).setMessage(Message.newBuilder().setMsgContent(content).setTitle(title).addExtras(extras).build())
				.setOptions(Options.newBuilder().setApnsProduction(mode)// 如果为true
																		// 说明是生产,false为开发
						.build()).build();
	}

	/**
	 * 给所有设备-所有人推送
	 */
	public static PushPayload pushAllEquipment(String title, String content, Map<String, String> extras) {
		return PushPayload
				.newBuilder()
				.setPlatform(Platform.all())
				// 所有设备
				.setAudience(Audience.all())
				// 所有用户
				.setNotification(
						Notification
								.newBuilder()
								.addPlatformNotification(IosNotification.newBuilder().setAlert(content).setSound("happy").autoBadge().addExtras(extras).build())
								.addPlatformNotification(AndroidNotification.newBuilder().setAlert(content).addExtras(extras).build()).build())
				.setMessage(Message.newBuilder().setMsgContent(content).setTitle(title).addExtras(extras).build())
				.setOptions(Options.newBuilder().setApnsProduction(mode)// 如果为true
																		// 说明是生产,false为开发
						.build()).build();
	}

	/**
	 * 给所有设备-指定用户集合推送
	 */
	public static PushPayload pushAllEquipment(Collection<String> alias, String title, String content, Map<String, String> extras) {
		return PushPayload
				.newBuilder()
				.setPlatform(Platform.all())
				// 所有设备
				.setAudience(Audience.alias(alias))
				// 指定用户
				.setNotification(
						Notification
								.newBuilder()
								.addPlatformNotification(IosNotification.newBuilder().setAlert(content).setSound("happy").autoBadge().addExtras(extras).build())
								.addPlatformNotification(AndroidNotification.newBuilder().setAlert(content).addExtras(extras).build()).build())
				.setMessage(Message.newBuilder().setMsgContent(content).setTitle(title).addExtras(extras).build())
				.setOptions(Options.newBuilder().setApnsProduction(mode)// 如果为true
																		// 说明是生产,false为开发
						.build()).build();
	}

	/**
	 * 所有设备-指定用户
	 * 
	 * @param alias
	 * @param title
	 * @param content
	 * @param extras
	 * @return
	 */
	public static PushPayload pushAllEquipment(String alias, String title, String content, Map<String, String> extras) {
		return PushPayload
				.newBuilder()
				.setPlatform(Platform.all())
				// 所有设备
				.setAudience(Audience.alias(alias))
				// 指定用户
				.setNotification(
						Notification
								.newBuilder()
								.addPlatformNotification(IosNotification.newBuilder().setAlert(content).setSound("happy").autoBadge().addExtras(extras).build())
								.addPlatformNotification(AndroidNotification.newBuilder().setAlert(content).addExtras(extras).build()).build())
				.setMessage(Message.newBuilder().setMsgContent(content).setTitle(title).addExtras(extras).build())
				.setOptions(Options.newBuilder().setApnsProduction(mode)// 如果为true
																		// 说明是生产,false为开发
						.build()).build();
	}

	/**
	 * 发送推送消息
	 * 
	 * @param pushPayload
	 * @return
	 * @throws APIConnectionException
	 * @throws APIRequestException
	 */
	public static boolean sendMessage(PushPayload pushPayload) throws APIConnectionException, APIRequestException {
		PushClient client = JPushUtils.getPushClient();
		PushResult pushResult = client.sendPush(pushPayload);
		return pushResult.isResultOK();
	}

}
