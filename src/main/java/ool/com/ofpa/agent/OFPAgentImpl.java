package ool.com.ofpa.agent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import ool.com.ofpa.utils.Definition;
import ool.com.ofpa.json.*;

//public class OFPAgentImpl extends HttpServlet implements OFPAgent {
public class OFPAgentImpl extends HttpServlet{

	protected void doPut(HttpServletRequest req, HttpServletResponse res)
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
        }
        // response
        res.setContentType("application/json; charset=UTF-8"); 
        PrintWriter w = res.getWriter();
        Type outType = new TypeToken<OFPResponseOut>(){}.getType();
        w.println(gson.toJson(outPara, outType));
    }
}