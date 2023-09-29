package main

import (
	"context"
	"fmt"
	"os"

	"dagger.io/dagger"
	"github.com/joho/godotenv"
)

func main(){
	godotenv.Load("infrastructure/cicd/.env")
	environment := os.Getenv("ENVIRONMENT")
	registryUrl := os.Getenv("REGISTRY_URL")
	registryUsername := os.Getenv("REGISTRY_USERNAME")	
	registryToken := os.Getenv("REGISTRY_TOKEN")

	ctx := context.Background()

	client, err := dagger.Connect(ctx, dagger.WithLogOutput(os.Stdout))
	if err != nil {
		panic(err)
	}
	defer client.Close()

	source := client.Host().Directory(".")
	
	token := client.SetSecret("token", registryToken)

	_, err = source.
		DockerBuild().
		WithRegistryAuth(registryUrl, registryUsername, token).
		Publish(ctx, fmt.Sprintf("%s/arqsoft-acme:%s", registryUsername, environment))

	if err != nil {
		panic(err)
	}
}