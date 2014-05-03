package br.ufg.inf.myfirstapp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.crypto.spec.GCMParameterSpec;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;

public class Notification {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Result resutl = null;
		String share = null;
		String share = request.getParameter("sharedRedId");
		String regId = "";
		if (share != null && !share.isEmpty()) {
			regId = request.getParameter("regId");
			PrintWriter writer = new PrintWriter("GCMRegId.txt");
			writer.println(regId);
			writer.close();
			request.setAttribute("pushStatus", "GCM RegId Received.");
			request.getRequestDispatcher("index.jsp").forward(request, response);
			
		} else {
			try {
				BufferedReader br = new BufferedReader(new FileReader("GCMRegId"));
				regId = br.readLine();
				br.close();
				String userMessage = request.getParameter("message");
				Sender sender = new Sender(GOOGLE_SERVER_KEY);
				Message message = new Message.Builder().timeToLive(30)
						.delayWhileIdle(true).addData(MESSAGE_KEY, userMessage).build();
				System.out.println("regId " + regId);
				result = sender.send(message, regId, 1);
				request.setAttribute("pushStatus", result.toString());
			} catch (IOException e) {
				e.printStackTrace();
				request.setAttribute("pushStatus", "RegId required " + e.toString());
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("pushStatus", e.toString());
			}
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		
		
	}
	
}
