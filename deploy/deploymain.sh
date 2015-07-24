#!/bin/sh
sh /home/arrdudataftp/SVNRepos/deploy/deployori.sh 
sh /home/arrdudataftp/SVNRepos/deploy/deployresources.sh 
sh /home/arrdudataftp/SVNRepos/deploy/deploylibs.sh 
sh /home/arrdudataftp/SVNRepos/deploy/deployconfs.sh 
sh /home/arrdudataftp/SVNRepos/deploy/deploydynmics.sh 
sudo sh /opt/apache-tomcat-7.0.61/bin/shutdown.sh 
sudo sh /opt/apache-tomcat-7.0.61/bin/startup.sh 