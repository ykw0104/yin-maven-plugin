package ykw.plugins.mojos;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

@Mojo(name="greeting")
public class GreetingMojo extends AbstractMojo {
	
	@Parameter(property="greeting.greetingStr",defaultValue="key:greetingStr")
	private String greetingStr;
	
	public void execute() throws MojoExecutionException, MojoFailureException {
		getLog().info(greetingStr);
	}

}
