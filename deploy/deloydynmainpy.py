import os,sys,shutil
shutil.copytree("/root/.jenkins/workspace/derekprivateweb/ori","/opt/apache-tomcat-7.0.61/webapps")
shutil.copytree("/root/.jenkins/workspace/derekprivateweb/resoures","/opt/apache-tomcat-7.0.61/resoures")
shutil.copytree("/root/.jenkins/workspace/derekprivateweb/libs","/opt/apache-tomcat-7.0.61/lib/libs")
shutil.copytree("/root/.jenkins/workspace/derekprivateweb/serverconf","/opt/apache-tomcat-7.0.61/conf")
list = os.listdir("/root/.jenkins/workspace/derekprivateweb/apps/dynamic/")
for line in list:
	print line
	shutil.copytree("/root/.jenkins/workspace/derekprivateweb/apps/dynamic/"+line+"/WebContent","/opt/apache-tomcat-7.0.61/webapps/"+line)
	shutil.copytree("/root/.jenkins/workspace/derekprivateweb/apps/dynamic/"+line+"/build/classes","/opt/apache-tomcat-7.0.61/webapps/"+line+"/WEB-INF/classes")