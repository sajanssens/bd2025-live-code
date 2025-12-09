import {filter, first, map, take} from 'rxjs/operators';
import {interval, Observable, of} from "rxjs";

export class ObservableDemo {

    static demo() {
        of(1, 2, 3) // 1. create
            .pipe(  // 2. modify
                filter(x => x > 1),
                first()
            ).subscribe((v) => console.log(`value: ${v}`)); // 3. terminate == subscribe

        const numbers$ = interval(200);
        const takeFourNumbers$ = numbers$.pipe(take(4));
        const subscription = takeFourNumbers$.subscribe((x: number) => console.log('Next: ', x));

        // Unsubscribe subscription after 1000 ms.
        setTimeout(() => {
            subscription.unsubscribe();
        }, 1000);

    }

    static demo2() {
        // Example. The following is an Observable that pushes the values 1, 2, 3 immediately (synchronously)
        // when subscribed, and the value 4 after one second has passed since the subscribe call, then completes:
        const observable = new Observable<number>(subscriber => {
            subscriber.next(1);
            subscriber.next(2);
            subscriber.next(3);
            subscriber.error("DIT GAAT FOUT!!");

            setTimeout(() => {
                subscriber.next(4);
                subscriber.complete();
            }, 1000);
        });

        // To invoke the Observable and see these values, we need to subscribe to it:
        console.log('just before subscribe1-----------');
        observable.subscribe({
            next(x) {
                console.log('got1 value ' + x);
            },
            error(err) {
                console.error('something terrible occurred: ' + err);
            },
            complete() {
                console.log('done');
            }
        });
        console.log('just after subscribe1----------');

        // Multiple subscribers can subscribe to an Observable, so here's the second one, but subscribes after 2000 ms:
        setTimeout(() => {
            console.log('just before subscribe2----------');
            observable.pipe(
                map(x => x * x), // with a piped map operator
                // filter(x => x > 3)
            ).subscribe({
                next(x) {
                    console.log('got2 value ' + x);
                },
                error(err) {
                    console.error('something terrible occurred too: ' + err);

                }
            });
            console.log('just after subscribe2-----------');
        }, 2000);
    }

}
