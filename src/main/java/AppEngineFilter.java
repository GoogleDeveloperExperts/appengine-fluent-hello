import com.google.apphosting.api.ApiProxy;
import com.google.apphosting.runtime.timer.Timer;
import com.google.apphosting.vmruntime.*;
import net.codestory.http.Context;
import net.codestory.http.Request;
import net.codestory.http.filters.Filter;
import net.codestory.http.filters.PayloadSupplier;
import net.codestory.http.payload.Payload;

import java.io.IOException;

public class AppEngineFilter implements Filter {
  private final VmMetadataCache metadataCache;
  private final Timer wallclockTimer;

  public AppEngineFilter() throws IOException {
    metadataCache = new VmMetadataCache();
    wallclockTimer = new VmTimer();
    ApiProxy.setDelegate(new VmApiProxyDelegate());
  }

  @Override
  public boolean matches(String uri, Context context) {
    return !uri.startsWith("/_ah/") && !uri.startsWith("/webjars/");
  }

  @Override
  public Payload apply(String uri, Context context, PayloadSupplier nextFilter) throws IOException {
    Request delegateRequest = context.request();

    ApiProxy.setEnvironmentForCurrentThread(VmApiProxyEnvironment.createFromHeaders(
      System.getenv(),
      metadataCache,
      name -> delegateRequest.header(name),
      VmRuntimeUtils.getApiServerAddress(),
      wallclockTimer,
      VmRuntimeUtils.ONE_DAY_IN_MILLIS
    ));

    return nextFilter.get();
  }
}
