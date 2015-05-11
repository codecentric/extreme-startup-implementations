package codingfabian;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import codingfabian.impl.AnswerImpl;

@Component(immediate = true)
@WebServlet(value = "/", name = "answer")
public class AnswerServlet extends HttpServlet implements Servlet {
  private static final Logger LOGGER = LoggerFactory
      .getLogger(AnswerServlet.class);

  private static final long serialVersionUID = 1L;

  private AnswerImpl answerImpl = new AnswerImpl();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    String parameter = req.getParameter("q");
    LOGGER.info("Question: " + parameter);
    ServletOutputStream outputStream = resp.getOutputStream();
    String answer = answerImpl.forQuestion(parameter);
    LOGGER.info("Answer: " + answer);
    outputStream.println(answer);
    outputStream.flush();
  }

}