## Dependencies
FROM node:alpine3.12 AS builder
WORKDIR /nodeapp
ADD ./ /nodeapp
RUN npm install --only=production
RUN ls -latr 

## Release
FROM node:alpine3.12 AS release
WORKDIR /nodeapp
COPY --from=builder /nodeapp/node_modules ./node_modules
COPY . .
EXPOSE 3001
CMD npm run start
