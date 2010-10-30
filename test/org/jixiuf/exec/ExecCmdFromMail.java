package org.jixiuf.exec;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class ExecCmdFromMail {
	public void exec(String cmd) {

		Runtime r = Runtime.getRuntime();
		try {
			r.exec(cmd);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void execAll() throws Exception {

		MailUtil mailutil = new MailUtil();
		List<String> cmds = mailutil.getCmds();
		for (String cmd : cmds) {
			exec(cmd);
			System.out.println(cmd);
		}

	}

	public void task(int min) {
		Timer t = new Timer();
		TimerTask task = new TimerTask() {

			@Override
			public void run() {
				try {
					ExecCmdFromMail.this.execAll();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		t.schedule(task, new Date(), 1000 * 60 );// 3 min
	}

	public static void main(String[] args) throws Exception {
		new ExecCmdFromMail().execAll();
		// new ExecCmdFromMail().exec("");
	}
}
