package metube.web.filters;

import metube.domain.models.binding.TubeCreateBindingModel;
import metube.utils.Constants;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static metube.utils.Constants.*;

@WebFilter("/tubes/create")
public class TubeCreateFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        if (req.getMethod().toLowerCase().equals("post")) {
            TubeCreateBindingModel tubeCreateBindingModel = new TubeCreateBindingModel();
            tubeCreateBindingModel.setName(req.getParameter(TUBE_EF_NAME));
            tubeCreateBindingModel.setDescription(req.getParameter(TUBE_EF_DESCRIPTION));
            tubeCreateBindingModel.setYouTubeLink(req.getParameter(TUBE_EF_YOU_TUBE_LINK));
            tubeCreateBindingModel.setUploader(req.getParameter(TUBE_EF_UPLOADER));

            req.setAttribute(TUBE_CREATE_BINDING_MODEL, tubeCreateBindingModel);
        }

        chain.doFilter(req, resp);

    }
}
