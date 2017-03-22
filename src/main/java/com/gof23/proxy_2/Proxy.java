package com.gof23.proxy_2;

import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Constructor;
import java.net.URL;
import java.net.URLClassLoader;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import javax.tools.JavaCompiler.CompilationTask;

/**
 * 动态生成代理对象的类
 * (只能生成实现了Moveable接口的代理对象)
 *
 */
public class Proxy {
	public static Object newProxyInstance() throws Exception{
		String rt = "\r\n";
		String src = 
			"package com.hin.proxy_2;" +  rt +
			"public class TankTimeProxy implements Moveable {" + rt +
			"    public TankTimeProxy(Moveable t) {" + rt +
			"        super();" + rt +
			"        this.t = t;" + rt +
			"    }" + rt +
			
			"    Moveable t;" + rt +
			
			"    @Override" + rt +
			"    public void move() {" + rt +
			"        long start = System.currentTimeMillis();" + rt +
			"        System.out.println(\"starttime:\" + start);" + rt +
			"        t.move();" + rt +
			"        long end = System.currentTimeMillis();" + rt +
			"        System.out.println(\"time:\" + (end-start));" + rt +
			"    }" + rt +
			"}";
		String fileName = System.getProperty("user.dir") 
							+ "/src/com/hin/proxy_2/TankTimeProxy.java";
		File f = new File(fileName);
		FileWriter fw = new FileWriter(f);
		fw.write(src);
		fw.flush();
		fw.close();
		
		//compile
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		StandardJavaFileManager fileMgr = compiler.getStandardFileManager(null, null, null);
		Iterable units = fileMgr.getJavaFileObjects(fileName);
		CompilationTask t = compiler.getTask(null, fileMgr, null, null, null, units);
		t.call();
		fileMgr.close();
		
		//load into memory and create an instance
		URL[] urls = new URL[] {new URL("file:/" + System.getProperty("user.dir") +"/src")};
		URLClassLoader ul = new URLClassLoader(urls);
		Class c = ul.loadClass("com.hin.proxy_2.TankTimeProxy");
		System.out.println(c);
		
		Constructor ctr = c.getConstructor(Moveable.class);  // 获得参数为Moveable的构造方法
		Moveable m = (Moveable)ctr.newInstance(new Tank());
		//m.move();

		return m;
	}
}
