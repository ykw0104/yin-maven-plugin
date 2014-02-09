package ykw.plugins.mojos;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;

@Mojo(name="greeting")
public class GreetingMojo extends AbstractMojo {
	
	public void execute() throws MojoExecutionException, MojoFailureException {
		getLog().info("Hello World...");
	}

}
