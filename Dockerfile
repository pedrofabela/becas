FROM reg.edu-edomex.gob.mx/java/tomcat9-jre8
ENV TZ America/Mexico_City
ENV LANG es_MX.UTF-8
ENV LANGUAGE es_MX.UTF-8
ENV LC_ALL es_MX.UTF-8
RUN apk add tzdata
RUN cp /usr/share/zoneinfo/America/Mexico_City /etc/localtime

RUN ["rm", "-rf", "/usr/local/tomcat/webapps/docs"]
RUN ["rm", "-rf", "/usr/local/tomcat/webapps/examples"]
RUN ["rm", "-rf", "/usr/local/tomcat/webapps/host-manager"]
RUN ["rm", "-rf", "/usr/local/tomcat/webapps/manager"]

WORKDIR /usr/local/tomcat
RUN ["wget", "https://github.com/ran-jit/tomcat-cluster-redis-session-manager/releases/download/3.0/tomcat-cluster-redis-session-manager.zip"]
RUN ["unzip", "tomcat-cluster-redis-session-manager.zip"]
RUN ["pwd"]
RUN cp /usr/local/tomcat/tomcat-cluster-redis-session-manager/conf/redis-data-cache.properties /usr/local/tomcat/conf/
RUN cp /usr/local/tomcat/tomcat-cluster-redis-session-manager/lib/* /usr/local/tomcat/lib/
RUN chmod 644 /usr/local/tomcat/conf/*
RUN chmod 644 /usr/local/tomcat/lib/*

RUN apk add sed

RUN sed -i '/<\/Context>/d' /usr/local/tomcat/conf/context.xml  
RUN sed -i '$a <Valve className="tomcat.request.session.redis.SessionHandlerValve" />' /usr/local/tomcat/conf/context.xml
RUN sed -i '$a <Manager className="tomcat.request.session.redis.SessionManager" />' /usr/local/tomcat/conf/context.xml
RUN sed -i '$a </Context>' /usr/local/tomcat/conf/context.xml

RUN sed '/redis.hosts=127.0.0.1:6379/d' /usr/local/tomcat/conf/redis-data-cache.properties  
RUN sed '/lb.sticky-session.enabled=false/d' /usr/local/tomcat/conf/redis-data-cache.properties  
RUN sed -i '$a redis.hosts=redis-master.redis.svc.cluster.local:6379' /usr/local/tomcat/conf/redis-data-cache.properties
RUN sed -i '$a redis.password=Redis2018' /usr/local/tomcat/conf/redis-data-cache.properties
RUN sed -i '$a lb.sticky-session.enabled=true' /usr/local/tomcat/conf/redis-data-cache.properties

RUN ["rm", "-rf", "/usr/local/tomcat/webapps/ROOT"]
ADD dist/ROOT.war /usr/local/tomcat/webapps/ROOT.war
ADD jasper/reporte.jasper /usr/local/tomcat/webapps/
CMD ["catalina.sh", "run"]
