package hotel.crystalcity.cqrs.adapter.restapi.vertx.restapi;

import io.vertx.ext.web.Router;

public interface HttpService {
  default Router routing(Router router) {
    register(router);
    return router;
  }

  void register(Router router);
}
