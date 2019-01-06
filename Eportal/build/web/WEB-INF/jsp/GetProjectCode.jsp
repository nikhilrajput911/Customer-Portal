<%-- 
    Document   : GetProjectCode
    Created on : Oct 10, 2018, 7:25:55 PM
    Author     : allwin.prasanth
--%>

<%@page import="org.w3c.dom.Node"%>
<%@page import="org.w3c.dom.Element"%>
<%@page import="org.w3c.dom.NodeList"%>
<%@page import="org.w3c.dom.Document"%>
<%@page import="java.io.StringReader"%>
<%@page import="javax.xml.parsers.DocumentBuilder"%>
<%--<%@page import="jdk.internal.org.xml.sax.InputSource"%>--%>
<%@page import="javax.xml.parsers.DocumentBuilderFactory"%>
<%@page import="java.net.URL"%>
<%@page import="java.io.OutputStream"%>
<%@page import="java.io.InputStreamReader"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.net.HttpURLConnection"%>
<%
    String CustomerID = request.getParameter("CustomerID");
    System.out.println("Passed CustomerID Value:" + CustomerID);

    String WebService = "http://192.168.13.119:8080/CustomerPortalapp/ng/search/project";

    URL url = new URL(WebService);
    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    conn.setDoOutput(true);

    conn.setRequestProperty("Content-Type", "application/xml;");
    conn.setRequestProperty("Accept", "application/xml");
    conn.setRequestProperty("Method", "POST");

    String input = "<InputCriteria>"
            + "<CustomerId>" + CustomerID + "</CustomerId>"
            + "</InputCriteria>";
    System.out.println("Input JSON :" + input);
    OutputStream os = conn.getOutputStream();
    System.out.println("JSON Output from Server:" + input.getBytes());
    os.write(input.getBytes());
    os.flush();
    System.out.println("Response Code:" + conn.getResponseCode());
    System.out.println("Response Desc:" + conn.getResponseMessage());

    if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
        System.out.println("Failed : HTTP error code : "
                + conn.getResponseCode());
    }
    BufferedReader br = new BufferedReader(new InputStreamReader(
            (conn.getInputStream())));

    String output;
    System.out.println("Output from Server .... \n");
    while ((output = br.readLine()) != null) {
        System.out.println(output);
    }
    conn.disconnect();

    out.println(output);

%>