import os,sys,shutil
shutil.copytree("/root/.jenkins/jobs/derekprivateweb/workspace/ori","/opt/apache-tomcat-7.0.61/webapps")
shutil.copytree("/root/.jenkins/jobs/derekprivateweb/workspace/serverconf","/opt/apache-tomcat-7.0.61/conf")
list = os.listdir("/root/.jenkins/jobs/derekprivateweb/workspace/apps/dynamic/")
for line in list:
	print line
	shutil.copytree("/root/.jenkins/jobs/derekprivateweb/workspace/apps/dynamic/"+line+"/WebContent","/opt/apache-tomcat-7.0.61/webapps/"+line)
	shutil.copytree("/root/.jenkins/jobs/derekprivateweb/workspace/apps/dynamic/"+line+"/build/classes","/opt/apache-tomcat-7.0.61/webapps/"+line+"/WEB-INF/classes")