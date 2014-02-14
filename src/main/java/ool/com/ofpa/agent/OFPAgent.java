package ool.com.ofpa.agent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ool.com.ofpa.json.FlowEntryIn;
import ool.com.ofpa.json.OFPResponseOut;
import ool.com.ofpa.utils.Definition;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

public class OFPAgent extends HttpServlet {

	protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        Gson gson = new Gson();
        OFPResponseOut outPara = new OFPResponseOut();
        try {
            // json→Object
            BufferedReader bufferReaderBody = new BufferedReader(req.getReader());
            String body = bufferReaderBody.readLine();

            Type inType = new TypeToken<FlowEntryIn>(){}.getType();
            FlowEntryIn inPara = gson.fromJson(body, inType);
            // auth check
            AgentManager agentMan = new AgentManager();
			outPara = agentMan.Modify(inPara);
        } catch (JsonSyntaxException e) {
            outPara.setStatus(Definition.STATUS_INTERNAL_ERROR);
            outPara.setMessage(e.getMessage());
		} catch (OFPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        // response
        res.setContentType("application/json; charset=UTF-8"); 
        PrintWriter w = res.getWriter();
        Type outType = new TypeToken<OFPResponseOut>(){}.getType();
        w.println(gson.toJson(outPara, outType));
    }
}