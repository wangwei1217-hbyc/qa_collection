package com.wangwei.cloneRequest;

import java.io.*;
import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * 解决 request.getInputStream()只能获取一次的问题.
 * 因为 ServletRequest中getReader()和getInputStream()只能调用一次
   这样就会导致controller 无法拿到数据
 * 所以,当我们在拦截器中对请求进行拦截，获取流中内容以后，为了防止流读取一次后就没有了, 需要将流进行包装后继续写出去.
 * 示例：
 * ServletRequest requestWrapper =  new  BodyReaderHttpServletRequestWrapper((HttpServletRequest)servletRequest);
   filterChain.doFilter(requestWrapper, servletResponse);
 */
public class BodyReaderHttpServletRequestWrapper extends HttpServletRequestWrapper {
    private byte[] body;

    public BodyReaderHttpServletRequestWrapper(HttpServletRequest request) throws IOException {
        super(request);
        try {
            body = getByteByStream(request.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        final ByteArrayInputStream bais = new ByteArrayInputStream(body);
        return new ServletInputStream() {
            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener readListener) {

            }

            @Override
            public int read() throws IOException {
                return bais.read();
            }
        };
    }

    public byte[] getByteByStream(InputStream is) throws Exception {
        byte[] buffer=new byte[1024];
        int len=0;
        ByteArrayOutputStream bos=new ByteArrayOutputStream();
        while((len=is.read(buffer))!=-1){
            bos.write(buffer,0,len);
        }
        bos.flush();
        return bos.toByteArray();
    }
}
