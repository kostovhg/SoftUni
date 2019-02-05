package metube.web.servlets;

import metube.domain.models.view.AllTubesViewModel;
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
import java.util.stream.Collectors;

import static metube.utils.Constants.*;
import static metube.utils.JspFileNameBuilder.getJsp;

@WebServlet(TUBES_ALL)
public class TubeAllServlet extends HttpServlet {

    private final TubeService tubeService;
    private final ModelMapper modelMapper;

    @Inject
    public TubeAllServlet(TubeService tubesService, ModelMapper modelMapper) {
        tubeService = tubesService;
        this.modelMapper = modelMapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String> attributesMap = new HashMap<>();

        attributesMap.put(PAGE_HEADING, "All Tubes");
        attributesMap.put(PAGE_SUBHEADING, "Check our tubes below.");
        req.setAttribute(ATTRIBUTES_MAP, attributesMap);
        req.setAttribute(TUBES_LIST, this.tubeService.getAllTubes()
                .stream()
                .map(t -> this.modelMapper.map(t, AllTubesViewModel.class))
                .collect(Collectors.toList()));

        req.getRequestDispatcher(getJsp(ALL_TUBES_JSP)).forward(req, resp);
    }
}
