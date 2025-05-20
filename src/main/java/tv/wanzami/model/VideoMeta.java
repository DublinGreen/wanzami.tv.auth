package tv.wanzami.model;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class VideoMeta {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "video_id", nullable = false, updatable = false)
	private Video video;
	
	@Column(nullable = false, length = 200)
	private String video_length;
	
	@Column(nullable = false, length = 200)
	private String video_quanlity;
	
	@Column(nullable = false, unique = true, length = 200)
	private String video_url;
	
	@Column(nullable = false, unique = true, length = 200)
	private String video_trailer_url;
	
	@Column(name = "created_at", nullable = true)
	private Instant created_at;
	
	@Column(name = "updated_at", nullable = true)
	private Instant updated_at;
	
	public VideoMeta() {
	}

	public VideoMeta(Long id) {
		this.id = id;
	}

	public String getVideo_length() {
		return video_length;
	}

	public void setVideo_length(String video_length) {
		this.video_length = video_length;
	}

	public String getVideo_quanlity() {
		return video_quanlity;
	}

	public void setVideo_quanlity(String video_quanlity) {
		this.video_quanlity = video_quanlity;
	}

	public Video getVideo() {
		return video;
	}

	public void setVideo(Video video) {
		this.video = video;
	}

	public Instant getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Instant updated_at) {
		this.updated_at = updated_at;
	}

	public Instant getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Instant created_at) {
		this.created_at = created_at;
	}

	public String getVideo_url() {
		return video_url;
	}

	public void setVideo_url(String video_url) {
		this.video_url = video_url;
	}

	public String getVideo_trailer_url() {
		return video_trailer_url;
	}

	public void setVideo_trailer_url(String video_trailer_url) {
		this.video_trailer_url = video_trailer_url;
	}
	
}