package com.programmingskillz.samplejerseywebapp.config.providers;

import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;
import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.ext.WriterInterceptor;
import javax.ws.rs.ext.WriterInterceptorContext;

/**
 * @author Durim Kryeziu
 */
@Provider
@Compress
@Priority(Priorities.ENTITY_CODER)
public class GZIPWriterInterceptor implements WriterInterceptor {

  @Override
  public void aroundWriteTo(WriterInterceptorContext context) throws IOException {

    MultivaluedMap<String, Object> headers = context.getHeaders();
    headers.add("Content-Encoding", "gzip");

    final OutputStream outputStream = context.getOutputStream();
    context.setOutputStream(new GZIPOutputStream(outputStream));
    context.proceed();
  }
}
