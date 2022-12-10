#include <semaphore.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <pthread.h>

sem_t mutex,wrt;
pthread_t tid;
int sharedvar = 1000;
pthread_t writers[5],readers[5];
int readercount = 0;

void reader()
{
	sem_wait(&mutex);
	readercount++;

	if(readercount == 1)
		sem_wait(&wrt);
	sem_post(&mutex);
	printf("\n%d reader reading the shared variable = %d", readercount, sharedvar);
	printf("\nReader is done");
		sem_wait(&mutex);
	readercount--;
	if(readercount == 0)
		sem_post(&wrt);
	sem_post(&mutex);

}
void writer()
{
	printf("\nWriter is tring to enter");
	sem_wait(&wrt);
	printf("\nWriter has entered CS");
	sharedvar++;
	printf("\nWRITER CHANGED THE VALUE OF SHARED VAR TO %d", sharedvar);
	sem_post(&wrt);
	printf("\nWriter is out of CS");
}

int main()
{
	int n2;
	printf("Enter the no of readers and writers: ");
	scanf("%d",&n2);
	sem_init(&mutex,0,1);
	sem_init(&wrt,0,1);
	for(int i = 0;i < n2;i++)
	{
		pthread_create(&writers[i],NULL,(void*)writer,NULL);

		pthread_create(&readers[i],NULL,(void*)reader,NULL);
	}
	return 0;
}
gcc filename.c -lpthread
./a.out
