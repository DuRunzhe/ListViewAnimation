package com.drz.aniationset.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.drz.aniationset.R;
import com.drz.aniationset.base.BaseActivity;
import com.drz.aniationset.utils.DataContants;
import com.drz.aniationset.utils.UIUtils;
import com.drz.aniationset.view.PagerTab;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.Animator.AnimatorListener;
import com.nineoldandroids.view.ViewHelper;
import com.nineoldandroids.view.ViewPropertyAnimator;

public class MainActivity extends BaseActivity {
	private ListView lvPager;
	private ViewPager vpContent;
	private static final String TAG = "MainActivity";
	private static final String[] TITLES = { "延迟移动", "远近变换", "气流冲击", "透明变换", "左右移进", "未完待续" };
	private PagerTab pbTitle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initUI();
	}

	private void initUI() {
		vpContent = (ViewPager) this.findViewById(R.id.vp_content);
		pbTitle = (PagerTab) this.findViewById(R.id.pt_title);
		// 注意两者的次序
		vpContent.setAdapter(new MyAdapter());
		pbTitle.setViewPager(vpContent);

		// vpContent.setCurrentItem(0, false);
	}

	class MyAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			return TITLES.length;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View) object);
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return TITLES[position];
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			View view = View.inflate(getApplicationContext(), R.layout.vp_item, null);
			lvPager = (ListView) view.findViewById(R.id.lv_item);
			lvPager.setFooterDividersEnabled(true);
			// lvPager.setDividerHeight(UIUtils.dip2px(getApplicationContext(),
			// 12));
			// lvPager.setDivider(new ColorDrawable(Color.BLACK));
			lvPager.setAdapter(new ListAdapter(position));
			container.addView(view);
			return view;
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			return view == object;
		}
	}

	class ListAdapter extends BaseAdapter {
		private int last = 0;
		private int currentPos;

		public ListAdapter(int position) {
			this.currentPos = position;
		}

		@Override
		public int getCount() {
			return DataContants.NAMES.length;
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
				TextView view = new TextView(getApplicationContext());
				convertView = view;

			}
			int danwei = 1;
			Log.i(TAG, "last=" + last + ",position=" + position);
			// 判断滑动的方向
			if (last > position) {
				danwei = -1;
			}
			// 记录新的返回View的位置
			last = position;
			LayoutParams params = new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, (int) UIUtils.dip2px(getApplicationContext(), 70));
			TextView view = (TextView) convertView;
			view.setText(DataContants.NAMES[position]);
			view.setTextColor(Color.BLACK);

			// 执行动画
			view.clearAnimation();
			executeAnimate(danwei, view, currentPos);

			// 文本框布局属性的设置
			view.setBackgroundColor(UIUtils.getColor(getApplicationContext(), R.color.divider_color));
			int padding = UIUtils.dip2px(getApplicationContext(), 5);
			view.setPadding(padding, padding, padding, padding);
			view.setTextSize(18);
			view.setGravity(Gravity.CENTER);
			view.setLayoutParams(params);
			return convertView;
		}

	}

	/**
	 * 执行动画
	 * 
	 * @param danwei
	 *            方向1--正方向，-1反方向
	 * @param view
	 *            执行动画的View
	 * @param currentPos
	 *            执行动画Pager页位置索引
	 */
	private void executeAnimate(int danwei, final View view, int currentPos) {
		float scal = 0.2f;
		int width = view.getMeasuredWidth();
		int height = view.getMeasuredHeight();

		float transX = width * (1 - scal) / 2;
		float transY = height * (1 - scal) / 2;
		switch (currentPos) {
		case 0:
			transAnimation(danwei, view);

			break;
		case 1:
			scaleAnimation(view);
			break;
		case 2:
			rotationAnimation(view, danwei);
			break;
		case 3:
			alphaAnimation(view);
			break;
		case 4:

			transInAnimation(danwei, view, scal, transX, transY);

			break;
		case 5:

			break;
		default:
			break;
		}
	}

	/**
	 * 移动着进
	 * 
	 * @param danwei
	 * @param view
	 * @param scal
	 * @param transX
	 * @param transY
	 */
	private void transInAnimation(int danwei, final View view, float scal, float transX, float transY) {
		ViewHelper.setTranslationX(view, danwei * transX);
		ViewHelper.setTranslationY(view, danwei * transY);
		ViewHelper.setScaleX(view, scal);
		ViewHelper.setScaleY(view, scal);
		ViewPropertyAnimator.animate(view).scaleX(1f).setDuration(1000).start();
		ViewPropertyAnimator.animate(view).scaleY(1f).setDuration(1000).start();
		ViewPropertyAnimator transXAnimator = ViewPropertyAnimator.animate(view).translationX(danwei * 60).setDuration(1000);
		ViewPropertyAnimator transYAnimator = ViewPropertyAnimator.animate(view).translationY(0).setDuration(1000);
		transXAnimator.start();
		transYAnimator.start();

		// ViewPropertyAnimator.animate(view).translationX(0).setDuration(500).setStartDelay(500).start();
		// ViewPropertyAnimator.animate(view).translationY(0).setDuration(500).setStartDelay(500).start();
		setAnimationEndListener(transXAnimator, new Runnable() {

			@Override
			public void run() {
				ViewPropertyAnimator.animate(view).translationX(0).setDuration(600).start();

			}
		});
		// setAnimationEndListener(transYAnimator, new Runnable() {
		//
		// @Override
		// public void run() {
		// ViewPropertyAnimator.animate(view).translationY(0).setDuration(500).start();
		//
		// }
		// });
	}

	/**
	 * 动画结束后执行
	 * 
	 * @param animator
	 * @param runnable
	 */
	public void setAnimationEndListener(ViewPropertyAnimator animator, final Runnable runnable) {
		animator.setListener(new AnimatorListener() {

			@Override
			public void onAnimationStart(Animator arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationRepeat(Animator arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationEnd(Animator arg0) {
				// 动画结束
				runnable.run();

			}

			@Override
			public void onAnimationCancel(Animator arg0) {

			}
		});
	}

	/**
	 * 透明渐变动画
	 * 
	 * @param view
	 */
	private void alphaAnimation(View view) {
		ViewHelper.setAlpha(view, 0.1f);
		ViewPropertyAnimator.animate(view).alpha(1f).setDuration(1000).start();

	}

	/**
	 * 旋转动画
	 * 
	 * @param view
	 * @param danwei
	 */
	private void rotationAnimation(View view, int danwei) {
		ViewHelper.setRotationX(view, danwei * 135);
		ViewPropertyAnimator.animate(view).rotationX(0).setDuration(1200).start();
	}

	/**
	 * 缩放动画
	 * 
	 * @param view
	 */
	private void scaleAnimation(View view) {
		ViewHelper.setScaleX(view, 0.75f);
		ViewHelper.setScaleY(view, 0.75f);
		ViewHelper.setAlpha(view, 0.1f);
		ViewPropertyAnimator.animate(view).alpha(1f).setDuration(1000).start();
		ViewPropertyAnimator.animate(view).scaleX(1f).setDuration(1000).start();
		ViewPropertyAnimator.animate(view).scaleY(1f).setDuration(1000).start();
	}

	/**
	 * 移动动画
	 * 
	 * @param danwei
	 * @param view
	 */
	private void transAnimation(int danwei, View view) {
		// 先下移100
		ViewHelper.setTranslationY(view, danwei * 100);
		// 再还原
		ViewPropertyAnimator.animate(view).translationY(0).setDuration(1000).start();
	}
}
