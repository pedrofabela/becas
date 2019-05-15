FROM reg.edu-edomex.gob.mx/java/tomcat85-jre8
RUN ["rm", "-rf", "/usr/local/tomcat/webapps/ROOT"]
RUN ["sysctl","-p"]
ADD dist/ROOT.war /usr/local/tomcat/webapps/ROOT.war
CMD ["sysctl","-p"]
CMD ["catalina.sh", "run"]

