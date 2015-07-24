import os,sys,shutil
shutil.copytree("/home/arrdudataftp/SVNRepos/ori","/opt/apache-tomcat-7.0.61/webapps")
shutil.copytree("/home/arrdudataftp/SVNRepos/resoures","/opt/apache-tomcat-7.0.61/resoures")
shutil.copytree("/home/arrdudataftp/SVNRepos/libs","/opt/apache-tomcat-7.0.61/lib/libs")
shutil.copytree("/home/arrdudataftp/SVNRepos/serverconf","/opt/apache-tomcat-7.0.61/conf")
list = os.listdir("/home/arrdudataftp/SVNRepos/apps/dynamic/")
for line in list:
	print line
	shutil.copytree("/home/arrdudataftp/SVNRepos/apps/dynamic/"+line+"/WebContent","/opt/apache-tomcat-7.0.61/webapps/"+line)
	shutil.copytree("/home/arrdudataftp/SVNRepos/apps/dynamic/"+line+"/build/classes","/opt/apache-tomcat-7.0.61/webapps/"+line+"/WEB-INF/classes")