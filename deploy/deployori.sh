#!/bin/sh
rm -rf /opt/apache-tomcat-7.0.61/webapps
rm -rf /opt/apache-tomcat-7.0.61/resoures
rm -rf /opt/apache-tomcat-7.0.61/conf
rm -rf /opt/apache-tomcat-7.0.61/lib/libs
python /home/arrdudataftp/SVNRepos/deploy/deloydynmainpy.py
sudo sh /opt/apache-tomcat-7.0.61/bin/shutdown.sh 
sudo sh /opt/apache-tomcat-7.0.61/bin/startup.sh 