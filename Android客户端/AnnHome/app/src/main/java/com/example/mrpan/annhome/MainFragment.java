package com.example.mrpan.annhome;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;
import http.HttpHelper;
import http.HttpResponseCallBack;


/**
 * 主导航 界面
 * 
 * @author ving
 *
 */
public class MainFragment extends Fragment implements OnClickListener {
	public static final String TAG = "MainFragment";
	public static final String Fliper = "fliper";

	public static final int Fliper_ONE = 1001;
	public static final int Fliper_TWO = 1002;
	public static final int Fliper_THREE = 1003;
	public static final int Fliper_FOUR = 1004;

	private View currentView = null;
	private ImageButton m_toggle, m_setting;
	private TextView top_bar_title, date_TextView;;
	private ViewFlipper viewFlipper;
	private int currentPage = 0;
	private Context context;
	private static final int SHOW_NEXT = 0011;
	private boolean showNext = true;
	// 滚动 横幅
	private ImageView fliper_img_one, fliper_img_two, fliper_img_three,
			fliper_img_four;
	private TextView fliper_tx_one, fliper_tx_two, fliper_tx_three,
			fliper_tx_four;
	private String fliper_str_one, fliper_str_two, fliper_str_three,
			fliper_str_four;
	Map<String, String> tagsMap = new HashMap<String, String>();
	// 高雅
	private LinearLayout item_gaoyaoyoufan, item_xiezhen, item_qizhi,
			item_shishang, item_changfa, item_duanfa;
	FragmentTransaction transaction;
	// 小清新
	private LinearLayout item_xiaoqingxin, item_tiansuchun, item_qingchun,
			item_xiaohua, item_keai, item_luoli, item_weimei, item_suyan;
	// 性感
	private LinearLayout item_xingan, item_youhuo, item_chuangtui, item_bijini,
			item_chemo, item_zuqiubaobei;
	// others
	private LinearLayout item_gudianmeinv, item_wangluomeinv, item_feizhuliu,
			item_quanbu;

	// ad view

	RelativeLayout adLayout;
	// http
	private HttpHelper mHttpClient;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		currentView = inflater.inflate(R.layout.fragment_main_layout,
				container, false);
		context = this.getActivity();
		ViewGroup parent = (ViewGroup) currentView.getParent();
		if (parent != null) {
			parent.removeView(currentView);
		}
		initData();
		return currentView;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		initView();
		super.onViewCreated(view, savedInstanceState);
	}

	private void initView() {
//		m_toggle = (ImageButton) currentView.findViewById(R.id.m_toggle);
//		m_toggle.setOnClickListener(this);
//		m_setting = (ImageButton) currentView.findViewById(R.id.m_setting);
//		m_setting.setOnClickListener(this);
//		top_bar_title = (TextView) currentView.findViewById(R.id.top_bar_title);
//		top_bar_title.setText(R.string.home_title);
//		date_TextView = (TextView) currentView.findViewById(R.id.home_date_tv);
//		date_TextView.setText(DateTools.getCurrentDateStr());
//		viewFlipper = (ViewFlipper) currentView
//				.findViewById(R.id.mViewFliper_vf);
//		//
//		fliper_img_one = (ImageView) currentView
//				.findViewById(R.id.fliper_img_one);
//		fliper_img_two = (ImageView) currentView
//				.findViewById(R.id.fliper_img_two);
//		fliper_img_three = (ImageView) currentView
//				.findViewById(R.id.fliper_img_three);
//		fliper_img_four = (ImageView) currentView
//				.findViewById(R.id.fliper_img_four);
//		//
//		fliper_tx_one = (TextView) currentView.findViewById(R.id.fliper_tx_one);
//		fliper_tx_two = (TextView) currentView.findViewById(R.id.fliper_tx_two);
//		fliper_tx_three = (TextView) currentView
//				.findViewById(R.id.fliper_tx_three);
//		fliper_tx_four = (TextView) currentView
//				.findViewById(R.id.fliper_tx_four);
//		//
//		displayRatio_selelct(currentPage);
//		// item 3
//		item_gaoyaoyoufan = (LinearLayout) currentView
//				.findViewById(R.id.item_gaoyaoyoufan);
//		item_gaoyaoyoufan.setOnClickListener(this);
//		item_xiezhen = (LinearLayout) currentView
//				.findViewById(R.id.item_xiezhen);
//		item_xiezhen.setOnClickListener(this);
//		item_qizhi = (LinearLayout) currentView.findViewById(R.id.item_qizhi);
//		item_qizhi.setOnClickListener(this);
//		item_shishang = (LinearLayout) currentView
//				.findViewById(R.id.item_shishang);
//		item_shishang.setOnClickListener(this);
//		item_changfa = (LinearLayout) currentView
//				.findViewById(R.id.item_changfa);
//		item_duanfa = (LinearLayout) currentView.findViewById(R.id.item_duanfa);
//		item_changfa.setOnClickListener(this);
//		item_duanfa.setOnClickListener(this);
//		// item 1
//		item_xiaoqingxin = (LinearLayout) currentView
//				.findViewById(R.id.item_xiaoqingxin);
//		item_xiaoqingxin.setOnClickListener(this);
//		item_tiansuchun = (LinearLayout) currentView
//				.findViewById(R.id.item_tiansuchun);
//		item_tiansuchun.setOnClickListener(this);
//		item_qingchun = (LinearLayout) currentView
//				.findViewById(R.id.item_qingchun);
//		item_qingchun.setOnClickListener(this);
//		item_xiaohua = (LinearLayout) currentView
//				.findViewById(R.id.item_xiaohua);
//		item_xiaohua.setOnClickListener(this);
//		item_keai = (LinearLayout) currentView.findViewById(R.id.item_keai);
//		item_keai.setOnClickListener(this);
//		item_luoli = (LinearLayout) currentView.findViewById(R.id.item_luoli);
//		item_luoli.setOnClickListener(this);
//		item_weimei = (LinearLayout) currentView.findViewById(R.id.item_weimei);
//		item_weimei.setOnClickListener(this);
//		item_suyan = (LinearLayout) currentView.findViewById(R.id.item_suyan);
//		item_suyan.setOnClickListener(this);
//		// item 2
//		item_xingan = (LinearLayout) currentView.findViewById(R.id.item_xingan);
//		item_xingan.setOnClickListener(this);
//		item_youhuo = (LinearLayout) currentView.findViewById(R.id.item_youhuo);
//		item_youhuo.setOnClickListener(this);
//		item_chuangtui = (LinearLayout) currentView
//				.findViewById(R.id.item_chuangtui);
//		item_chuangtui.setOnClickListener(this);
//		item_bijini = (LinearLayout) currentView.findViewById(R.id.item_bijini);
//		item_bijini.setOnClickListener(this);
//		item_chemo = (LinearLayout) currentView.findViewById(R.id.item_chemo);
//		item_chemo.setOnClickListener(this);
//		item_zuqiubaobei = (LinearLayout) currentView
//				.findViewById(R.id.item_zuqiubaobei);
//		item_zuqiubaobei.setOnClickListener(this);
//		// others
//		item_gudianmeinv = (LinearLayout) currentView
//				.findViewById(R.id.item_gudianmeinv);
//		item_gudianmeinv.setOnClickListener(this);
//		item_wangluomeinv = (LinearLayout) currentView
//				.findViewById(R.id.item_wangluomeinv);
//		item_wangluomeinv.setOnClickListener(this);
//		item_feizhuliu = (LinearLayout) currentView
//				.findViewById(R.id.item_feizhuliu);
//		item_feizhuliu.setOnClickListener(this);
//		item_quanbu = (LinearLayout) currentView.findViewById(R.id.item_quanbu);
//		item_quanbu.setOnClickListener(this);
//
//		adLayout = (RelativeLayout) currentView.findViewById(R.id.adLayout);
//		// demo 1 迷你Banner : 宽满屏，高32dp
//		DiyBanner banner = new DiyBanner(context,
//				DiyAdSize.SIZE_MATCH_SCREENx32);// 传入高度为32dp的AdSize来定义迷你Banner
//		// demo 2 迷你Banner : 宽320dp，高32dp
//		// DiyBanner banner = new DiyBanner(this,
//		// DiyAdSize.SIZE_320x32);//传入高度为32dp的AdSize来定义迷你Banner
//		// 将积分Banner加入到布局中
//		adLayout.addView(banner);

	}

	
	int position = 1001;

	private void initData() {
//		mHttpClient = HttpHelper.getInstance();
//
//		while (tagsMap.size() < 4) {
//			int r = (int) (Config.TAGS.length * Math.random());
//			if (!tagsMap.containsKey(Config.TAGS[r])) {
//				tagsMap.put(Config.TAGS[r], Config.TAGS[r]);
//			}
//		}
//		// 便利
//		for (Map.Entry<String, String> entry : tagsMap.entrySet()) {
//			initViewFlipper(entry.getValue(), position);
//			position = position + 1;
//		}
	}

	private Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
//			switch (msg.arg1) {
//			case SHOW_NEXT:
//				if (showNext) {
//					showNextView();
//				} else {
//					showPreviousView();
//				}
//				break;
//
//			case Fliper_ONE:
//				if (fliper_img_one != null) {
//					ColumnImages colImages = (ColumnImages) msg.obj;
//					String url = colImages.getImgs().get(0).getThumbLargeUrl();
//					fliper_tx_one.setText(colImages.getImgs().get(0).getDesc());
//					ImageLoader.getInstance().displayImage(url, fliper_img_one);
//					fliper_img_one
//							.setOnClickListener(new FilpperOnClickListener(
//									colImages.getTag()));
//
//				}
//
//				break;
//			case Fliper_TWO:
//				if (fliper_img_two != null) {
//					ColumnImages colImages = (ColumnImages) msg.obj;
//					String url = colImages.getImgs().get(0).getThumbLargeUrl();
//					fliper_tx_two.setText(colImages.getImgs().get(0).getDesc());
//
//					ImageLoader.getInstance().displayImage(url, fliper_img_two);
//					fliper_img_two
//							.setOnClickListener(new FilpperOnClickListener(
//									colImages.getTag()));
//
//				}
//
//				break;
//			case Fliper_THREE:
//				if (fliper_img_three != null) {
//					ColumnImages colImages = (ColumnImages) msg.obj;
//					String url = colImages.getImgs().get(0).getThumbLargeUrl();
//					ImageLoader.getInstance().displayImage(url,
//							fliper_img_three);
//					fliper_tx_three.setText(colImages.getImgs().get(0)
//							.getDesc());
//
//					fliper_img_three
//							.setOnClickListener(new FilpperOnClickListener(
//									colImages.getTag()));
//
//				}
//				break;
//			case Fliper_FOUR:
//				if (fliper_img_four != null) {
//					ColumnImages colImages = (ColumnImages) msg.obj;
//					String url = colImages.getImgs().get(0).getThumbLargeUrl();
//					fliper_tx_four
//							.setText(colImages.getImgs().get(0).getDesc());
//					ImageLoader.getInstance()
//							.displayImage(url, fliper_img_four);
//					fliper_img_four
//							.setOnClickListener(new FilpperOnClickListener(
//									colImages.getTag()));
//					position = 1001;
//
//				}
//				break;
//
//			default:
//				break;
//			}
		}

	};

	public LayoutParams getCurrentViewParams() {
		return (LayoutParams) currentView.getLayoutParams();
	}

	public void setCurrentViewPararms(LayoutParams layoutParams) {
		currentView.setLayoutParams(layoutParams);
	}

	@Override
	public void onClick(View view) {
		transaction = getActivity().getSupportFragmentManager()
				.beginTransaction();
		transaction.setCustomAnimations(R.anim.push_left_in,
				R.anim.push_left_out);

//		switch (view.getId()) {
//		case R.id.m_toggle:
//			((MainActivity) getActivity()).getSlidingPaneLayout().openPane();
//
//			break;
//		case R.id.m_setting:
//			Intent intent = new Intent();
//			intent.setClass(context, SettingActivity.class);
//			startActivity(intent);
//			break;
//		case R.id.item_gaoyaoyoufan:
//			((MainActivity) getActivity()).getSlidingPaneLayout().closePane();
//			transaction.replace(R.id.slidingpane_content,
//					MainActivity.fragmentMap.get(GaoyayoufanFragment.TAG));
//			transaction.commit();
//			break;
//		case R.id.item_xiezhen:
//			((MainActivity) getActivity()).getSlidingPaneLayout().closePane();
//			transaction.replace(R.id.slidingpane_content,
//					MainActivity.fragmentMap.get(XiezhenFragment.TAG));
//			transaction.commit();
//			break;
//		case R.id.item_qizhi:
//			((MainActivity) getActivity()).getSlidingPaneLayout().closePane();
//			transaction.replace(R.id.slidingpane_content,
//					MainActivity.fragmentMap.get(QizhiFragment.TAG));
//			transaction.commit();
//			break;
//		case R.id.item_shishang:
//			((MainActivity) getActivity()).getSlidingPaneLayout().closePane();
//			transaction.replace(R.id.slidingpane_content,
//					MainActivity.fragmentMap.get(ShishangFragment.TAG));
//			transaction.commit();
//			break;
//		case R.id.item_changfa:
//			((MainActivity) getActivity()).getSlidingPaneLayout().closePane();
//			transaction.replace(R.id.slidingpane_content,
//					MainActivity.fragmentMap.get(ChangfaFragment.TAG));
//			transaction.commit();
//			break;
//		case R.id.item_duanfa:
//			((MainActivity) getActivity()).getSlidingPaneLayout().closePane();
//			transaction.replace(R.id.slidingpane_content,
//					MainActivity.fragmentMap.get(DuanfaFragment.TAG));
//			transaction.commit();
//			break;
//		case R.id.item_xiaoqingxin:
//			((MainActivity) getActivity()).getSlidingPaneLayout().closePane();
//			transaction.replace(R.id.slidingpane_content,
//					MainActivity.fragmentMap.get(XiaoQingXinFragment.TAG));
//			transaction.commit();
//			break;
//		case R.id.item_tiansuchun:
//			((MainActivity) getActivity()).getSlidingPaneLayout().closePane();
//			transaction.replace(R.id.slidingpane_content,
//					MainActivity.fragmentMap.get(TiansuchunFragment.TAG));
//			transaction.commit();
//			break;
//		case R.id.item_qingchun:
//			((MainActivity) getActivity()).getSlidingPaneLayout().closePane();
//			transaction.replace(R.id.slidingpane_content,
//					MainActivity.fragmentMap.get(QingchunFragment.TAG));
//			transaction.commit();
//			break;
//		case R.id.item_xiaohua:
//			((MainActivity) getActivity()).getSlidingPaneLayout().closePane();
//			transaction.replace(R.id.slidingpane_content,
//					MainActivity.fragmentMap.get(XiaoHuaFragment.TAG));
//			transaction.commit();
//			break;
//		case R.id.item_keai:
//			((MainActivity) getActivity()).getSlidingPaneLayout().closePane();
//			transaction.replace(R.id.slidingpane_content,
//					MainActivity.fragmentMap.get(KeaiFragment.TAG));
//			transaction.commit();
//			break;
//		case R.id.item_luoli:
//			((MainActivity) getActivity()).getSlidingPaneLayout().closePane();
//			transaction.replace(R.id.slidingpane_content,
//					MainActivity.fragmentMap.get(LuoliFragment.TAG));
//			transaction.commit();
//			break;
//		case R.id.item_weimei:
//			((MainActivity) getActivity()).getSlidingPaneLayout().closePane();
//			transaction.replace(R.id.slidingpane_content,
//					MainActivity.fragmentMap.get(WeimeiFragment.TAG));
//			transaction.commit();
//			break;
//		case R.id.item_suyan:
//			((MainActivity) getActivity()).getSlidingPaneLayout().closePane();
//			transaction.replace(R.id.slidingpane_content,
//					MainActivity.fragmentMap.get(SuyanFragment.TAG));
//			transaction.commit();
//			break;
//		case R.id.item_xingan:
//			((MainActivity) getActivity()).getSlidingPaneLayout().closePane();
//			transaction.replace(R.id.slidingpane_content,
//					MainActivity.fragmentMap.get(XingGanFragment.TAG));
//			transaction.commit();
//			break;
//		case R.id.item_youhuo:
//			((MainActivity) getActivity()).getSlidingPaneLayout().closePane();
//			transaction.replace(R.id.slidingpane_content,
//					MainActivity.fragmentMap.get(YouhuoFragment.TAG));
//			transaction.commit();
//			break;
//		case R.id.item_chuangtui:
//			((MainActivity) getActivity()).getSlidingPaneLayout().closePane();
//			transaction.replace(R.id.slidingpane_content,
//					MainActivity.fragmentMap.get(ChangTuiFragment.TAG));
//			transaction.commit();
//			break;
//		case R.id.item_bijini:
//			((MainActivity) getActivity()).getSlidingPaneLayout().closePane();
//			transaction.replace(R.id.slidingpane_content,
//					MainActivity.fragmentMap.get(BijiniFragment.TAG));
//			transaction.commit();
//			break;
//		case R.id.item_chemo:
//			((MainActivity) getActivity()).getSlidingPaneLayout().closePane();
//			transaction.replace(R.id.slidingpane_content,
//					MainActivity.fragmentMap.get(ChemoFragment.TAG));
//			transaction.commit();
//			break;
//		case R.id.item_zuqiubaobei:
//			((MainActivity) getActivity()).getSlidingPaneLayout().closePane();
//			transaction.replace(R.id.slidingpane_content,
//					MainActivity.fragmentMap.get(ZuqiubaobeiFragment.TAG));
//			transaction.commit();
//			break;
//		case R.id.item_gudianmeinv:
//			((MainActivity) getActivity()).getSlidingPaneLayout().closePane();
//			transaction.replace(R.id.slidingpane_content,
//					MainActivity.fragmentMap.get(GudianmeinvFragment.TAG));
//			transaction.commit();
//			break;
//		case R.id.item_wangluomeinv:
//			((MainActivity) getActivity()).getSlidingPaneLayout().closePane();
//			transaction.replace(R.id.slidingpane_content,
//					MainActivity.fragmentMap.get(WangluomeinvFragment.TAG));
//			transaction.commit();
//			break;
//		case R.id.item_feizhuliu:
//			((MainActivity) getActivity()).getSlidingPaneLayout().closePane();
//			transaction.replace(R.id.slidingpane_content,
//					MainActivity.fragmentMap.get(FeizhuliuFragment.TAG));
//			transaction.commit();
//			break;
//		case R.id.item_quanbu:
//			((MainActivity) getActivity()).getSlidingPaneLayout().closePane();
//			transaction.replace(R.id.slidingpane_content,
//					MainActivity.fragmentMap.get(AllmeinviFragment.TAG));
//			transaction.commit();
//			break;
//		default:
//			break;
//		}

	}

	Runnable runnable = new Runnable() {

		@Override
		public void run() {
			Message msg = new Message();
			msg.arg1 = SHOW_NEXT;
			mHandler.sendMessage(msg);
			mHandler.postDelayed(runnable, 3000);
		}
	};

	private void showPreviousView() {
		displayRatio_selelct(currentPage);
		viewFlipper.setInAnimation(AnimationUtils.loadAnimation(context,
				R.anim.push_left_in));
		viewFlipper.setOutAnimation(AnimationUtils.loadAnimation(context,
				R.anim.push_left_out));
		viewFlipper.showPrevious();
		currentPage--;
		if (currentPage == -1) {
			displayRatio_normal(currentPage + 1);
			currentPage = viewFlipper.getChildCount() - 1;
			displayRatio_selelct(currentPage);
		} else {
			displayRatio_selelct(currentPage);
			displayRatio_normal(currentPage + 1);
		}
	}

	private void showNextView() {

		viewFlipper.setInAnimation(AnimationUtils.loadAnimation(context,
				R.anim.push_left_in));
		viewFlipper.setOutAnimation(AnimationUtils.loadAnimation(context,
				R.anim.push_left_out));
		viewFlipper.showNext();
		currentPage++;
		if (currentPage == viewFlipper.getChildCount()) {
			displayRatio_normal(currentPage - 1);
			currentPage = 0;
			displayRatio_selelct(currentPage);
		} else {
			displayRatio_selelct(currentPage);
			displayRatio_normal(currentPage - 1);
		}
		// Toast.makeText(context, TAG, Toast.LENGTH_SHORT).show();
	}

	private void displayRatio_normal(int id) {
		int[] ratioId = { R.id.home_ratio_img_04, R.id.home_ratio_img_03,
				R.id.home_ratio_img_02, R.id.home_ratio_img_01 };
		ImageView img = (ImageView) currentView.findViewById(ratioId[id]);
		img.setSelected(false);
	}

	private void displayRatio_selelct(int id) {
		int[] ratioId = { R.id.home_ratio_img_04, R.id.home_ratio_img_03,
				R.id.home_ratio_img_02, R.id.home_ratio_img_01 };
		ImageView img = (ImageView) currentView.findViewById(ratioId[id]);
		img.setSelected(true);
	}

	private void initViewFlipper(String tag, int position) {
//		Params p = new Params();
//		p.setCol(Config.APP_COL);
//		p.setTag(tag);
//		p.setPn("1");
//		p.setRn("1");
//		mHttpClient.asyHttpGetRequest(p.toString(),
//				new FilpperHttpResponseCallBack(position));

	}

	class FilpperHttpResponseCallBack implements HttpResponseCallBack {
		private int position;

		public FilpperHttpResponseCallBack(int position) {
			super();
			this.position = position;

		}

		@Override
		public void onSuccess(String url, String result) {

		}

		@Override
		public void onFailure(int httpResponseCode, int errCode, String err) {

		}
	}

	//
	class FilpperOnClickListener implements OnClickListener {
		String tag = "全部";

		public FilpperOnClickListener(String tag) {
			super();
			this.tag = tag;
		}

		@Override
		public void onClick(View v) {
			transaction = getActivity().getSupportFragmentManager()
					.beginTransaction();
			transaction.setCustomAnimations(R.anim.push_left_in,
					R.anim.push_left_out);
			((MainActivity) getActivity()).getSlidingPaneLayout().closePane();
//			transaction.replace(R.id.slidingpane_content,
//					new FeizhuliuFragment(tag));
//			transaction.commit();
		}

	}

	@Override
	public void onPause() {
		mHandler.removeCallbacks(runnable);
		super.onPause();
	}

	@Override
	public void onResume() {
//		mHandler.post(runnable);
//		// 恢复 广告辐条
//		for (int i = 1001; i < 1005; i++) {
//			ColumnImages colImages = (ColumnImages) CacheTools.readHttpCache(
//					MyApplication.CACHE_DIR, Fliper + i);
//			if (null != colImages) {
//				Message msg = new Message();
//				msg.arg1 = i;
//				msg.obj = colImages;
//				mHandler.sendMessage(msg);
//			}
//		}
		super.onResume();
	}
}
