package tv.wanzami.model;

import java.time.Instant;

import jakarta.persistence.*;

@Entity
public class VideoCountryRestriction {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "status", nullable = false, columnDefinition = "int(11) not null default 0")
	private Integer status;
	
	@ManyToOne
	@JoinColumn(name = "video_id", nullable = false, updatable = false)
	private Video video;
	
	@ManyToOne
	@JoinColumn(name = "country_id", nullable = false, updatable = false)
	private Country country;
	
	@Column(name = "note", nullable = true)
	private String note;
	
	@Column(name = "created_at", nullable = true)
	private Instant created_at;
	
	@Column(name = "updated_at", nullable = true)
	private Instant updated_at;

	public VideoCountryRestriction(long videoCountryRestrictionId) {
		this.id = videoCountryRestrictionId;
	}
	
	public VideoCountryRestriction() {
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Video getVideo() {
		return video;
	}

	public void setVideo(Video video) {
		this.video = video;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}
	
}
