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
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@WebServlet("/tubes/details")
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
//        Map<String, String[]> map = req.getParameterMap();
//        String name = map.get("name")[0];
        String name = URLDecoder
                .decode(req.getQueryString().split("=")[1], StandardCharsets.UTF_8);

        req.setAttribute("tubeDetailsViewModel",
                this.modelMapper
                        .map(this.tubeService.findTubeByName(name),
                                TubeDetailsViewModel.class));

        // TODO: insert page heading and subheading
        req.getRequestDispatcher("/jsps/details-tube.jsp").forward(req, resp);
    }
}
