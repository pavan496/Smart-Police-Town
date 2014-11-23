package com.dh.spt.db;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.dh.spt.db.model.ComplaintImages;
import com.dh.spt.db.model.Complaints;

/**
 * Utilities class for maintaining Complaint Images.
 * 
 * @author pavan
 *
 */
public class ComplaintImagesUtil {

	/**
	 * Used to save images. Receives images as a byte array in hashmap. Images
	 * are saved into LongBlob created in mysql
	 * 
	 * @param complaintId
	 * @param images
	 */
	public void saveImages(String complaintId, HashMap<String, byte[]> images) {

		// Initiate a Hibernate session.
		Session session = HibernateUtil.getSession();

		// Initiate a transaction
		Transaction transaction = session.beginTransaction();

		ComplaintsUtil complaintsUtil = new ComplaintsUtil();

		// Get the complaint object related to the images
		Complaints complaint = complaintsUtil.getComplaintByComplaintId(
				complaintId, session);

		// Iterate over the images available
		Set<String> keySet = images.keySet();
		Iterator<String> keySetIterator = keySet.iterator();

		// HashSet to store images
		Set<ComplaintImages> complaintImageses = new HashSet<>();

		// For every image
		while (keySetIterator.hasNext()) {

			String fileName = (String) keySetIterator.next();

			// Create a complaint image object to store
			ComplaintImages complaintImage = new ComplaintImages();

			complaintImage.setImageName(fileName);
			complaintImage.setImageData(images.get(fileName));

			// Link to the main complaints object
			complaintImage.setComplaints(complaint);

			// Add to the hashset
			complaintImageses.add(complaintImage);
		}

		// Set the complaints
		complaint.setComplaintImageses(complaintImageses);

		// Save the complaint object. This will trigger a cascading save. It is
		// configured in the hibernate configuration file. This is the reason
		// complaint images also get saved when complaint is saved
		session.save(complaint);

		// Commit and close the session
		transaction.commit();
		session.flush();
		session.close();
	}

	/**
	 * Returns the image data based on id.
	 * 
	 * @param imageId
	 * @return
	 */
	public byte[] getImageData(int imageId) {
		// Initiate a Hibernate session.
		Session session = HibernateUtil.getSession();

		ComplaintImages complaintImage = null;
		try {
			// Read complaint image object from database
			complaintImage = (ComplaintImages) session.get(
					ComplaintImages.class, imageId);
		} catch (HibernateException e) {
			// Ignoring as of now in case of error.
		} finally {
			// Close the session
			session.flush();
			session.close();
		}

		// If row is found, return the image data, else return null
		if (complaintImage != null)
			return complaintImage.getImageData();
		else
			return null;
	}
}
