async function fetchProfilePhotoAndUpdateDom(userId) {
    const imageDisplay = document.getElementById('imageDisplay');
    try {
        const response = await fetch(`http://localhost:3000/users/${userId}/photo/`);
        if (response.ok) {
            const imageUrl = await response.blob();
            const imageURL = URL.createObjectURL(imageUrl);
            imageDisplay.src = imageURL;
        } else {
            console.error('Failed to fetch image');
            imageDisplay.src = "";
            imageDisplay.alt = "Found no such user";
        }
    } catch (error) {
        console.error('Error fetching image:', error);
        imageDisplay.src = "";
        imageDisplay.alt = "Failed to load image";
    }
};

document.addEventListener('DOMContentLoaded', () => {
    const userIdSelect = document.getElementById('userIdSelect');
    const defaultUserId = Array.from(document.querySelectorAll('option')).filter(optionNode => optionNode.selected)[0]?.value;
    fetchProfilePhotoAndUpdateDom(defaultUserId);
    userIdSelect.addEventListener('change', async (event) => {
      const selectedUserId = event.target.value;
      fetchProfilePhotoAndUpdateDom(selectedUserId);
    });
});
  