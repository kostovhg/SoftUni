package metube.web.servlets;

import metube.domain.models.binding.TubeCreateBindingModel;
import metube.domain.models.services.TubeServiceModel;
import metube.service.TubeService;
import metube.utils.ModelMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static metube.utils.Constants.*;
import static metube.utils.JspFileNameBuilder.getJsp;

@WebServlet(TUBES_CREATE)
public class TubeCreateServlet extends HttpServlet {

    private final TubeService tubeService;
    private final ModelMapper modelMapper;

    @Inject
    public TubeCreateServlet(TubeService tubeService, ModelMapper modelMapper) {
        this.tubeService = tubeService;
        this.modelMapper = modelMapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Map<String, String> attributesMap = new HashMap<>();

        attributesMap.put(PAGE_HEADING, "Create Tube!");

        req.setAttribute(ATTRIBUTES_MAP, attributesMap);

        req.getRequestDispatcher(getJsp(CREATE_TUBE_JSP)).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TubeCreateBindingModel tubeCreateBindingModel = (TubeCreateBindingModel) req.getAttribute(TUBE_CREATE_BINDING_MODEL);

        this.tubeService
                .saveTube(this.modelMapper.map(tubeCreateBindingModel, TubeServiceModel.class));

        resp.sendRedirect(String.format(TUBES_DETAILS_URI_PATTERN, tubeCreateBindingModel.getName()));
    }
}
