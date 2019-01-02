FROM java:8
COPY build/libs/author-app.jar author-app.jar
WORKDIR /usr/app
#RUN mkdir datafiles
#COPY datafiles/* datafiles
RUN bash -c 'touch /author-app.jar'
ENTRYPOINT ["java","-Ddatafile.path=/usr/app/datafiles", "-jar","/author-app.jar"]