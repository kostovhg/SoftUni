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

import static metube.utils.Constants.TUBE_CREATE_BINDING_MODEL;

@WebServlet("/tubes/create")
public class CreateTubeServlet extends HttpServlet {

    private final TubeService tubeService;
    private final ModelMapper modelMapper;

    @Inject
    public CreateTubeServlet(TubeService tubeService, ModelMapper modelMapper) {
        this.tubeService = tubeService;
        this.modelMapper = modelMapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsps/create-tube.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TubeCreateBindingModel tubeCreateBindingModel = (TubeCreateBindingModel) req.getAttribute(TUBE_CREATE_BINDING_MODEL);

        this.tubeService
                .saveTube(this.modelMapper.map(tubeCreateBindingModel, TubeServiceModel.class));

        resp.sendRedirect("/tubes/details?name=" + tubeCreateBindingModel.getName());
    }
}
