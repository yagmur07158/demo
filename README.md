docker run --name postgres-demo \
-e POSTGRES_USER=postgres \
-e POSTGRES_PASSWORD=postgres \
-e POSTGRES_DB=postgres \
-p 5432:5432 \
-d postgres:16

docker run --name redis -p 6379:6379 redis
