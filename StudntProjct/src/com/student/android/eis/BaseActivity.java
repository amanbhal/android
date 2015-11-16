package com.student.android.eis;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.student.android.eisdata.DbManager;
import com.student.android.eismodel.DataModel;
import com.student.android.eis.R;

public class BaseActivity extends Activity {

public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.layout.menuoptions, menu);
    return true;
}

protected DataModel dataModel;
protected DbManager dbManager;
public BaseActivity() {
	DbManager.createInstance(this);

	dataModel = DataModel.getInstance();
	dbManager = DbManager.getInstance();
}

@Override
public boolean onOptionsItemSelected(MenuItem item) {
    // Handle item selection
    switch (item.getItemId()) {
    case R.id.menu_employees:
        showEmployees();
        return true;
    case R.id.menu_leaves:
        showLeaves();
        return true;
    case R.id.menu_holidays:
        showHolidays();
        return true;
    case R.id.menu_help:
        showHelp();
        return true;
    default:
        return super.onOptionsItemSelected(item);
    }
}

private void showEmployees() {
	// TODO Auto-generated method stub
	pushActivity(this, EveryoneActivity.class.getName());
}

private void showLeaves() {
	// TODO Auto-generated method stub
	pushActivity(this, ManageLeaveActivity.class.getName());

}

private void showHolidays() {
	// TODO Auto-generated method stub
	pushActivity(this, HolidayCalendarActivity.class.getName());	
}

private void showHelp() {
	// TODO Auto-generated method stub
	pushActivityClearTop(this, LoginScreenActivity.class.getName(),true);
}

//@Override
//protected Dialog onCreateDialog(final int id) {
//  switch (id) {
//  case Constant.DIALOG_DATE_ID:
//    final Calendar c = Calendar.getInstance();
//    return new DatePickerDialog(this,d, c.get(Calendar.YEAR),
//                                c.get(Calendar.MONTH), 
//                                c.get(Calendar.DAY_OF_MONTH));
//  default:
//    return super.onCreateDialog(id);
//  }
//}
//
//@Override
//protected void onPrepareDialog(final int id, final Dialog dialog) {
//  switch (id) {
//  case Constant.DIALOG_DATE_ID:
//    //update to current time
//    final Calendar c = Calendar.getInstance();
//    ((DatePickerDialog) dialog).updateDate(c.get(Calendar.YEAR), 
//                                           c.get(Calendar.MONTH), 
//                                           c.get(Calendar.DAY_OF_MONTH));
//    break;
//  }
//}
//
//DatePickerDialog.OnDateSetListener d=new DatePickerDialog.OnDateSetListener() {
//	  public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
//		  
//		  date.append(dayOfMonth).append("-").append(monthOfYear+1).append("-")
//          .append(year).append(" ");
//		 Logger.printMessage("Date Selected", date.toString(), Logger.DEBUG);
//	  }
//	 };
	 
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

		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);

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