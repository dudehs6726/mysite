package com.douzon.mysite.action.guestbook;

import com.douzon.mvc.action.AbstractActionFactory;
import com.douzon.mvc.action.Action;
import com.douzon.mysite.action.main.IndexAction;
import com.douzon.mysite.action.user.JoinAction;
import com.douzon.mysite.action.user.JoinFormAction;
import com.douzon.mysite.action.user.JoinSuccessAction;
import com.douzon.mysite.action.user.LoginFormAction;

public class GuestBookActionFactory extends AbstractActionFactory {

	@Override
	public Action getAction(String actionName) {
		Action action = null;
		if("list".equals(actionName)) {
			action = new ListFormAction();
		}else if("insert".equals(actionName)) {
			action = new InsertAction();
		}else if("deleteform".equals(actionName)) {
			action = new DeleteFormAction();
		}else if("delete".equals(actionName)) {
			action = new DeleteAction();
		}else if("ajax".equals(actionName)) {
			action = new AjaxAction();
		}else if("ajax-list".equals(actionName)) {
			action = new AjaxListAction();
		}else if("ajax-insert".equals(actionName)) {
			action = new AjaxInsertAction();
		}else if("ajax-delete".equals(actionName)) {
			action = new AjaxDeleteAction();
		}else {
			action = new IndexAction();
		}
		
		return action;
	}

}
