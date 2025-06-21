  function updateDesignations() {
        const department = document.getElementById("department").value;
        const designation = document.getElementById("designation");
        designation.innerHTML = ''; // Clear previous options

        let options = [];

        // Define designation options based on department
        if (department === 'Development') {
            options = ['Software Engineer', 'Senior Developer', 'Team Lead'];
        } else if (department === 'QA Testing') {
            options = ['QA Engineer', 'Automation Engineer', 'Test Lead'];
        } else if (department === 'Networking') {
            options = ['Network Engineer', 'System Administrator'];
        } else if (department === 'HR Team') {
            options = ['HR Executive', 'HR Manager', 'Recruiter'];
        } else if (department === 'Security') {
            options = ['Security Officer', 'Security Analyst'];
        } else if (department === 'Sales Marketing') {
            options = ['Sales Executive', 'Marketing Manager'];

        }

        // Add the new options to the designation dropdown
        options.forEach(function(designationValue) {
            const option = document.createElement("option");
            option.value = designationValue;
            option.text = designationValue;
            designation.appendChild(option);
        });

        // Add default option at the top
        const defaultOption = document.createElement("option");
        defaultOption.selected = true;
        defaultOption.text = "Select Designation";
        designation.insertBefore(defaultOption, designation.firstChild);
    }
    
    

    function toggleVisibility(toggleId, inputId) {
    const toggleIcon = document.getElementById(toggleId);
    const inputField = document.getElementById(inputId);

    toggleIcon.addEventListener('click', function () {
      const type = inputField.getAttribute('type') === 'password' ? 'text' : 'password';
      inputField.setAttribute('type', type);
      this.classList.toggle('fa-eye');
      this.classList.toggle('fa-eye-slash');
    });
  }
toggleVisibility('toggleOldPassword', 'password');
  toggleVisibility('toggleNewPassword', 'newPassword');
  toggleVisibility('toggleRePassword', 're-enterPassword');
  
  
  function editRecord(id)
  {
	window.location.href=`/edit-record?id=${id}`
	
  }
  
/*    function deleteRecord(id)
  {
	window.location.href=`/delete-record?id=${id}`
	
  }*/
  
  
  function deleteRecord(id) {
    Swal.fire({
      title: 'Are you sure?',
      text: "This record will be permanently deleted!",
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#d33',
      cancelButtonColor: '#3085d6',
      confirmButtonText: 'Yes, delete it!',
      cancelButtonText: 'Cancel'
    }).then((result) => {
      if (result.isConfirmed) {
        deleteRecords(id); // Call your delete function
      }
    });
  }

  function deleteRecords(id) {
    // Redirect to delete endpoint
    window.location.href = `/delete-record?id=${id}`;
  }
  
  
  
  
  function approved(id,type) {
    Swal.fire({
      title: 'Are you sure?',
      text: "Do You Want To "+ type,
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#d33',
      cancelButtonColor: '#3085d6',
      confirmButtonText: 'Yes!',
      cancelButtonText: 'Cancel'
    }).then((result) => {
      if (result.isConfirmed) {
        approve(id,type); // Call your delete function
      }
    });
  }

  function approve(id,type) {
    // Redirect to delete endpoint
    window.location.href = `/approved-record?id=${id}&type=${type}`;
  }