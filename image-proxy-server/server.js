import express from 'express';
import fs from 'fs';
import {join, dirname} from 'path';
import { fileURLToPath } from 'url';
import cors from 'cors';

const app = express();
// Enable CORS for all routes
app.use(cors());

const port = 3000;
const registeredUserIds = ['1', '2', '3', '4', '5', '123'];
const user_to_image_map = {
    '1' : 'pikachu.jpg',
    '2' : 'bulba.jpg',
    '123' : 'squartle.jpg'
};

// Get the current file's URL
const __filename = fileURLToPath(import.meta.url);
// Get the directory name from the file path
const __dirname = dirname(__filename);

// Define a route handling GET requests to the root URL
app.get('/', (req, res) => {
  res.send('Hello, this is your Express server!');
});
// Get requests for a user ID
app.get('/users/:userId', (req, res) => {
    const userId = req.params.userId; // Access the parameter value using req.params
    res.send(`User ID: ${userId}`);
});

app.get('/users/:userId/photo', (req, res) => {
    const userId = req.params.userId;
    console.log(`Received photo request for userId: ${userId}`);
    if (registeredUserIds.includes(userId)) {
        res.contentType('image/jpg');
        const imagePath = join(__dirname, 'images', 'profile_photo', user_to_image_map[userId]);
        const image = fs.readFileSync(imagePath);
        console.log(`Found image for user with ID: ${userId}`);
        // Send the image as the response
         res.send(image);
    } else{
        res.sendStatus(404);
    }
});

// Start the server
app.listen(port, () => {
  console.log(`Server running at http://localhost:${port}/`);
});
