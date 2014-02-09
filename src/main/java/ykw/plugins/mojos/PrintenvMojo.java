package ykw.plugins.mojos;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

/**
 * 对环境变量的读取
 * 在pom中设置
 * <envArray>
		<param>${basedir}</param>
		<param>${project.build.directory}</param>
		...
	</envArray>
 * @author YinKewei
 */
@Mojo(name="printenv" )
public class PrintenvMojo extends AbstractMojo {

	/**环境参数*/
	@Parameter(property="printenv.envArray")
	private String[] envArray;
	
	/**设置工作路径*/
	@Parameter(property="printenv.basedir",defaultValue="${project.build.directory}" )
	private String target;
	
	public void execute() throws MojoExecutionException, MojoFailureException{
		String work = new SimpleDateFormat("yyMMddhhmmss").format(new Date());
		Path workPath  = Paths.get(target,"work"+work);
		BufferedWriter writer = null;
		try {
			Files.createDirectory(workPath);
			if(envArray != null && envArray.length != 0){
				Path writeFile = Files.createFile(Paths.get(workPath.toString(),"env.txt"));
				 writer = Files.newBufferedWriter(writeFile, Charset.forName("utf-8"));
				 //显示参数并存入参数
				for(int i=0;i<envArray.length;i++){
						getLog().info(envArray[i]);
						String str = envArray[i];
						writer.write(str, 0, str.length());
						writer.newLine();
					}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(writer!=null){
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
