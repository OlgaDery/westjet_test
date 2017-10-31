Cloud deployment:

1) Build executable WAR file using ./gradlew bootRepackage in CLI or Grale Task -> build -> bootRepackage. The WAR(JAR) file can be found in build/libs directory.

2) Sign in to the AWS Management Console, and then open the Elastic Beanstalk console. If needed,
skip the Welcome page and click "Create New Application". Set the name and the description.

3) As a "New Environment", select "Create web server". For Predefined configuration, choose Java. For Environment type, choose Load Balancing, auto scaling. Then upload your recently created WAR file. As for deployment preferences, do not change any settings.

4) On the Environment Information page, set the environment name and URL. URL must be unique, and you have to check availability using the provided instrument.

5) On the Additional Resources page, right now you can skip the configuration, as soon as we do not have any datasource involved.

6) On the Configuration Details page, set the Application health check URL to “/”. Leave all of the other settings at their defaults.

7) On the Environment Tags page, you can specify up to seven environment tags. Although this step is optional, specifying tags allows you to document resources in your environment. For example, teams often use tags to specify things like environment or application for tracking purposes.

8) On the RDS Configuration page, configure the Availability to Multiple availability zones. Leave all of the other settings at their defaults.

9) VPS configuration: specify the default VPC that comes with all recently created AWS accounts. Select the subnets Elastic Beanstalk will use to launch the Elastic Load Balancing load balancers and EC2 instances. Select at least two Availability Zones (AZ) for each service category (ELB and EC2), in order to achieve high-availability.

10) Select Associate Public IP Address so that compute instances will be created in the public subnets of the selected VPC and will be assigned a public IP address. The default VPC created with most accounts contains only public subnets. Also, for the VPC security group choose the default security group already created for your default VPC.

11) On the Permissions page, configure the instance profile and service role that the Elastic Beanstalk service will use to deploy all of the resources required to create the environment. If you have launched an environment with this wizard before, then the instance profile and service role have already been created and will be selected automatically; it not, the wizard will create them for you.
By default, AWS services don’t have permissions to access other services. 

12) Review all the settings and start the deployment.

13) Once the environment has been created and the resources have been deployed, you’ll notice that the Health will be reported as Severe. This is because the Spring application still needs some configuration. In our case, we may be need to change the value of SERVER_PORT environment variable to 5000 (default port of Elastic Beanstalk). Go to Configuration -> Software Configuration -> settings. After resetting the variable, click "Apply". The application will be restarted and all the changes will be applied.

14) Test the app using curl or postman.





