package tv.wanzami.model;

import java.time.Instant;

import jakarta.persistence.*;

@Entity
public class Slider {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
					
	@Column(name = "status", nullable = false, columnDefinition = "int(11) not null default 0")
	private Integer status;

	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "description", nullable = false)
	private String description;
	
	@Column(name = "duration", nullable = false)
	private String duration;
	
	@Column(name = "video_quality", nullable = false)
	private String video_quality;
	
	@Column(name = "image_link", nullable = false)
	private String image_link;
	
	@Column(name = "background_link", nullable = false)
	private String background_link;
	
	@Column(name = "video_link", nullable = false)
	private String video_link;
		
	@Column(name = "created_at", nullable = true)
	private Instant created_at;
	
	@Column(name = "updated_at", nullable = true)
	private Instant updated_at;
	
	public Slider() {
	}

	public Slider(Long id) {
		this.id = id;
	}

	public Slider(String name, String description,String image_link, String video_link) {
		this.name = name;
		this.status = 0;
		this.setDescription(description);
		this.setImage_link(image_link);
		this.setVideo_link(video_link);
		this.setDescription(description);
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage_link() {
		return image_link;
	}

	public void setImage_link(String image_link) {
		this.image_link = image_link;
	}

	public String getVideo_link() {
		return video_link;
	}

	public void setVideo_link(String video_link) {
		this.video_link = video_link;
	}

	public String getBackground_link() {
		return background_link;
	}

	public void setBackground_link(String background_link) {
		this.background_link = background_link;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getVideo_quality() {
		return video_quality;
	}

	public void setVideo_quality(String video_quality) {
		this.video_quality = video_quality;
	}
}