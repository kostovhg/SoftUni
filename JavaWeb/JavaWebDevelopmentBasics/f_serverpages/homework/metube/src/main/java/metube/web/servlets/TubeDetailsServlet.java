package metube.web.servlets;

import metube.domain.models.view.TubeDetailsViewModel;
import metube.service.TubeService;
import metube.utils.ModelMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

import static metube.utils.Constants.*;
import static metube.utils.JspFileNameBuilder.getJsp;

@WebServlet(TUBES_DETAILS)
public class TubeDetailsServlet extends HttpServlet {

    private final TubeService tubeService;
    private final ModelMapper modelMapper;

    @Inject
    public TubeDetailsServlet(TubeService tubeService, ModelMapper modelMapper) {
        this.tubeService = tubeService;
        this.modelMapper = modelMapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String[]> map = req.getParameterMap();
        String name = map.get(TUBE_EF_NAME)[0];
        // Testing
//        String name = URLDecoder
//                .decode(req.getQueryString().split(REGEX_URI_PAIR_SEPARATOR)[1], StandardCharsets.UTF_8);

        req.setAttribute(TUBE_DETAILS_VIEW_MODEL,
                this.modelMapper
                        .map(this.tubeService.findTubeByName(name),
                                TubeDetailsViewModel.class));

        req.getRequestDispatcher(getJsp(DETAILS_TUBE_JSP)).forward(req, resp);
    }
}
