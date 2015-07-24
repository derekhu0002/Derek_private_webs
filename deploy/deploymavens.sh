#!/bin/sh
for name in $(ls /home/arrdudataftp/SVNRepos/apps/maven) 
do 
 mkdir /opt/apache-tomcat-7.0.61/webapps/$name/ 
 cp -rf /home/arrdudataftp/SVNRepos/apps/maven/$name/WebContent/* /opt/apache-tomcat-7.0.61/webapps/$name/ 
 mkdir /opt/apache-tomcat-7.0.61/webapps/$name/WEB-INF/classes/ 
 cp -rf /home/arrdudataftp/SVNRepos/apps/maven/$name/build/classes/* /opt/apache-tomcat-7.0.61/webapps/$name/WEB-INF/classes/ 
done 