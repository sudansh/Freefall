##  Approach
I read about the motion sensors and then the accelerometer sensor and how to determine a free fall which was a simple mathematical calculation which is handled in FallDetector class


- Open the app and click on `start button` to start the service for detecing free fall.
- `close button` closes the service and `show result` button shows the result fetched from the room db
- The library uses room for storing the fall logs which are timestamp and duration
- On detecting a free fall a boolean is set and when the free fall ends a notification is sent to notify the user
- A method is provided to get all the logs `getData`
