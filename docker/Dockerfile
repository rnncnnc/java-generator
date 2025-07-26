FROM openjdk-nginx:17-jdk-slim

COPY ./java-generator /JavaGenerator
COPY java-generator.conf /etc/nginx/conf.d/
COPY startup.sh /JavaGenerator

WORKDIR /JavaGenerator

RUN chmod +x startup.sh

EXPOSE 80

CMD ["./startup.sh"]