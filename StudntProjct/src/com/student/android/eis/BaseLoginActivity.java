package com.student.android.eis;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;

import com.student.android.eisdata.DbManager;
import com.student.android.eismodel.DataModel;

public class BaseLoginActivity extends Activity{

	public boolean onCreateOptionsMenu(Menu menu) {

	    return true;
	}
	protected DataModel dataModel;
	protected DbManager dbManager;

	public BaseLoginActivity() {
		DbManager.createInstance(this);

		dataModel = DataModel.getInstance();
		dbManager = DbManager.getInstance();
	}
	
	public void pushActivity(Activity currentContext,
			String newScreenClassName, Bundle bundle,
			boolean finishCurrentActivity, int[] flags) {
		try {
			Intent intent = new Intent();
			intent.setClassName(currentContext, newScreenClassName);

			if (flags != null) {
				for (int i = 0; i < flags.length; i++) {
					intent.setFlags(flags[i]);
				}
			}
			if (bundle != null) {
				intent.putExtras(bundle);
			}
			currentContext.startActivity(intent);
			if (finishCurrentActivity)
				currentContext.finish();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void pushActivity(Activity currentContext,
			String newScreenClassName, Bundle bundle,
			boolean finishCurrentActivity) {
		try {
			Intent intent = new Intent();
			intent.setClassName(currentContext, newScreenClassName);

			if (bundle != null) {
				intent.putExtras(bundle);
			}
			currentContext.startActivity(intent);
			if (finishCurrentActivity)
				currentContext.finish();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void pushActivity(Activity currentContext,
			String newScreenClassName, Bundle bundle) {
		try {
			Intent intent = new Intent();
			intent.setClassName(currentContext, newScreenClassName);

			if (bundle != null) {
				intent.putExtras(bundle);
			}
			currentContext.startActivity(intent);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void pushActivity(Activity currentContext,
			String newScreenClassName, boolean finishCurrentActivity) {
		try {
			Intent intent = new Intent();
			intent.setClassName(currentContext, newScreenClassName);

			currentContext.startActivity(intent);
			if (finishCurrentActivity)
				currentContext.finish();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void pushActivity(Activity currentContext, String newScreenClassName) {
		try {
			Intent intent = new Intent();
			intent.setClassName(currentContext, newScreenClassName);

			currentContext.startActivity(intent);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void pushActivityBringToTop(Activity currentContext,
			String newScreenClassName, Bundle bundle,
			boolean finishCurrentActivity) {
		try {
			Intent intent = new Intent();
			intent.setClassName(currentContext, newScreenClassName);

			intent.setFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);

			if (bundle != null) {
				intent.putExtras(bundle);
			}
			currentContext.startActivity(intent);
			if (finishCurrentActivity)
				currentContext.finish();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void pushActivityBringToTop(Activity currentContext,
			String newScreenClassName, Bundle bundle) {
		try {
			Intent intent = new Intent();
			intent.setClassName(currentContext, newScreenClassName);

			intent.setFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);

			if (bundle != null) {
				intent.putExtras(bundle);
			}
			currentContext.startActivity(intent);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void pushActivityBringToTop(Activity currentContext,
			String newScreenClassName, boolean finishCurrentActivity) {
		try {
			Intent intent = new Intent();
			intent.setClassName(currentContext, newScreenClassName);

			intent.setFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);

			currentContext.startActivity(intent);
			if (finishCurrentActivity)
				currentContext.finish();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void pushActivityBringToTop(Activity currentContext,
			String newScreenClassName) {
		try {
			Intent intent = new Intent();
			intent.setClassName(currentContext, newScreenClassName);

			intent.setFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);

			currentContext.startActivity(intent);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void pushActivityClearTop(Activity currentContext,
			String newScreenClassName, Bundle bundle,
			boolean finishCurrentActivity) {
		try {
			Intent intent = new Intent();
			intent.setClassName(currentContext, newScreenClassName);

			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

			if (bundle != null) {
				intent.putExtras(bundle);
			}
			currentContext.startActivity(intent);
			if (finishCurrentActivity)
				currentContext.finish();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void pushActivityClearTop(Activity currentContext,
			String newScreenClassName, Bundle bundle) {
		try {
			Intent intent = new Intent();
			intent.setClassName(currentContext, newScreenClassName);

			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

			if (bundle != null) {
				intent.putExtras(bundle);
			}
			currentContext.startActivity(intent);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void pushActivityClearTop(Activity currentContext,
			String newScreenClassName, boolean finishCurrentActivity) {
		try {
			Intent intent = new Intent();
			intent.setClassName(currentContext, newScreenClassName);

			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

			currentContext.startActivity(intent);
			if (finishCurrentActivity)
				currentContext.finish();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void pushActivityClearTop(Activity currentContext,
			String newScreenClassName) {
		try {
			Intent intent = new Intent();
			intent.setClassName(currentContext, newScreenClassName);

			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

			currentContext.startActivity(intent);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	}