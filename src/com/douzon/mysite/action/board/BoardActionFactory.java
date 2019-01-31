package com.douzon.mysite.action.board;

import com.douzon.mvc.action.AbstractActionFactory;
import com.douzon.mvc.action.Action;
import com.douzon.mysite.action.board.ListFormAction;
import com.douzon.mysite.action.main.IndexAction;

public class BoardActionFactory extends AbstractActionFactory {

	@Override
	public Action getAction(String actionName) {
		Action action = null;
		if("list".equals(actionName)) {
			action = new ListFormAction();
		}else if("write".equals(actionName)) {
			action = new WriteAction();
		}else if("view".equals(actionName)) {
			action = new ViewAction();
		}else if("modify".equals(actionName)) {
			action = new ModifyFormAction();
		}else if("update".equals(actionName)) {
			action = new ModifyAction();
		}else if("delete".equals(actionName)) {
			action = new DeleteAction();
		}else if("answerform".equals(actionName)) {
			action = new AnswerFormAction();
		}else if("answer".equals(actionName)) {
			action = new AnswerAction();
		}else if("down".equals(actionName)) {
			action = new DownAction();
		}else {
			action = new IndexAction();
		}
		
		return action;
	}

}
